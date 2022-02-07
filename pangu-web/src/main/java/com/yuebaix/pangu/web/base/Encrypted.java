package com.yuebaix.pangu.web.base;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.*;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.yuebaix.pangu.common.util.JacksonUtil;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import java.security.KeyPair;

/**
 * <h2>加密签名数据体</h2>
 * <hr/>
 * <p>RSA密钥（密钥位数：1024 密钥格式：PKCS#8 公钥标准：X.509 私钥标准：PKCS#8）</p>
 * <p>AES密钥（密钥位数：128）</p>
 * <br/>
 * <p>SHA256</p>
 * <p>RSA  RSA/ECB/PKCS1Padding</p>
 * <p>SHA256WithRSA  RSASSA-PKCS1-v1_5</p>
 * <p>AES  AES/ECB/PKCS5Padding iv为空 （此模式下PKCS5Padding与PKCS7Padding等价）</p>
 * <br/>
 * <p>【RSA私钥和公钥文件格式 (pkcs#7, pkcs#8, pkcs#12, pem)】https://blog.csdn.net/tuhuolong/article/details/42778945</p>
 * <p>【iOS常用加密算法】https://mbd.baidu.com/ma/s/ojbmZVXm</p>
 * <p>【证书生成地址】https://www.bejson.com/enc/rsa</p>
 * <p>【证书转换地址】https://www.ssleye.com/web/pkcs</p>
 *
 * <p>签名验签加密（验签在解密之前，性能友好，同时有时间戳来控制报文有效期，密文用base64传输，减少包体积）</p>
 * <br/>
 * <p>yuebaix peace out.welcome to follow my github https://github.com/yuebaix</p>
 */
@Getter
public class Encrypted<T> {
    //JSON数据字段参考(不作字段使用，仅供文档使用)
    private T _refer;
    //密钥密文
    private String key;
    //时间戳
    private Long t;
    //数据密文
    private String data;
    //签名
    private String sign;

    //服务端加密
    public static <T> Encrypted<T> encrypt(T pojo, String pvt) {
        Encrypted<T> encrypted = new Encrypted<>();
        //时间戳
        Long t = System.currentTimeMillis();

        //1.随机生成AES密钥
        SecretKey aesKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue());
        byte[] aesKeyBytes = aesKey.getEncoded();

        //2.RSA私钥加密AES密钥生成Base64文本密钥密文
        RSA rsa = SecureUtil.rsa(pvt, null);
        String key = rsa.encryptBase64(aesKeyBytes, KeyType.PrivateKey);

        //数据JSON
        String json = JacksonUtil.write(pojo);
        byte[] jsonBytes = json.getBytes();

        //3.AES加密数据JSON生成Base64文本数据密文
        AES aes = SecureUtil.aes(aesKeyBytes);
        String data = aes.encryptBase64(jsonBytes);

        //4.SHA256withRSA对 key+t+data 加签生成Base64文本签名密文
        String msg = StringUtils.join(key, String.valueOf(t), data);
        Sign sha256withRSA = SecureUtil.sign(SignAlgorithm.SHA256withRSA, pvt, null);
        String sign = Base64.encode(sha256withRSA.sign(msg.getBytes()));

        encrypted.key = key;
        encrypted.t = t;
        encrypted.data = data;
        encrypted.sign = sign;
        return encrypted;
    }

    //服务端解密
    public static <T> T decrypt(Encrypted<T> encrypted, String pvt, Class<T> clazz, Integer ttl) {
        String key = encrypted.getKey();
        Long t = encrypted.getT();
        String data = encrypted.getData();
        String sign = encrypted.getSign();

        if (ttl == null) {
            ttl = 10;
        }
        //1.验证时间戳生命周期
        PreCheck.checkArgument(System.currentTimeMillis() < t + ttl * 1000, "报文已失效");

        //2.SHA256对 key+t+data 进行验签
        //拼接 key+t+data 生成签名原文
        String msg = StringUtils.join(key, String.valueOf(t), data);
        //对签名原文签名生成sha256十六进制字符串，即中间签名
        String midSign = SecureUtil.sha256().digestHex(msg.getBytes());
        //拼接 midSign+t 生成二次签名原文
        String midMsg = StringUtils.join(midSign, String.valueOf(t));
        //对二次签名原文签名生成Base64数据密文
        String digestSign = Base64.encode(SecureUtil.sha256().digest(midMsg.getBytes()));
        boolean isVerified = digestSign.equals(sign);
        PreCheck.checkArgument(isVerified, "验签失败");

        //3.RSA私钥解密密文密钥获得AES密钥
        RSA rsa = SecureUtil.rsa(pvt, null);
        byte[] aesKeyBytes = rsa.decrypt(key, KeyType.PrivateKey);

        //4.AES解密数据密文获得数据JSON
        AES aes = SecureUtil.aes(aesKeyBytes);
        String json = aes.decryptStr(Base64.decode(data));

        //JSON反序列化
        T decrypted = JacksonUtil.read(json, clazz);
        return decrypted;
    }

    //客户端加密
    public static <T> Encrypted<T> clientEncrypt(T pojo, String pub) {
        Encrypted<T> encrypted = new Encrypted<>();
        //时间戳
        Long t = System.currentTimeMillis();

        //1.随机生成AES密钥
        SecretKey aesKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue());
        byte[] aesKeyBytes = aesKey.getEncoded();

        //2.RSA私钥加密AES密钥生成Base64文本密钥密文
        RSA rsa = SecureUtil.rsa(null, pub);
        String key = rsa.encryptBase64(aesKeyBytes, KeyType.PublicKey);

        //数据JSON
        String json = JacksonUtil.write(pojo);
        byte[] jsonBytes = json.getBytes();

        //3.AES加密数据JSON生成Base64文本数据密文
        AES aes = SecureUtil.aes(aesKeyBytes);
        String data = aes.encryptBase64(jsonBytes);

        //4.SHA256对数据加签生成Base64文本签名密文
        //拼接 key+t+data 生成签名原文
        String msg = StringUtils.join(key, String.valueOf(t), data);
        //对签名原文签名生成sha256十六进制字符串，即中间签名
        String midSign = SecureUtil.sha256().digestHex(msg.getBytes());
        //拼接 midSign+t 生成二次签名原文
        String midMsg = StringUtils.join(midSign, String.valueOf(t));
        //对二次签名原文签名生成Base64数据密文
        String sign = Base64.encode(SecureUtil.sha256().digest(midMsg.getBytes()));

        encrypted.key = key;
        encrypted.t = t;
        encrypted.data = data;
        encrypted.sign = sign;
        return encrypted;
    }

    //客户端解密
    public static <T> T clientDecrypt(Encrypted<T> encrypted, String pub, Class<T> clazz, Integer ttl) {
        String key = encrypted.getKey();
        Long t = encrypted.getT();
        String data = encrypted.getData();
        String sign = encrypted.getSign();

        if (ttl == null) {
            ttl = 10;
        }
        //1.验证时间戳生命周期
        PreCheck.checkArgument(System.currentTimeMillis() < t + ttl * 1000, "报文已失效");

        //2.SHA256withRSA对 key+t+data 进行验签
        byte[] signBytes = Base64.decode(sign);
        String msg = StringUtils.join(key, String.valueOf(t), data);
        Sign sha256withRSA = SecureUtil.sign(SignAlgorithm.SHA256withRSA, null, pub);
        boolean isVerified = sha256withRSA.verify(msg.getBytes(), signBytes);
        PreCheck.checkArgument(isVerified, "验签失败");

        //3.RSA私钥解密密文密钥获得AES密钥
        RSA rsa = SecureUtil.rsa(null, pub);
        byte[] aesKeyBytes = rsa.decrypt(key, KeyType.PublicKey);

        //4.AES解密数据密文获得数据JSON
        AES aes = SecureUtil.aes(aesKeyBytes);
        String json = aes.decryptStr(Base64.decode(data));

        //JSON反序列化
        T decrypted = JacksonUtil.read(json, clazz);
        return decrypted;
    }

    //生成公私钥
    public static String[] genPvtPub() {
        //生成RSA密钥对
        KeyPair pair = SecureUtil.generateKeyPair(AsymmetricAlgorithm.RSA.getValue());
        String pvt = Base64.encode(pair.getPrivate().getEncoded());
        String pub = Base64.encode(pair.getPublic().getEncoded());
        return new String[] {pvt, pub};
    }
}
