package com.yuebaix.pangu.test.controller.demo;

import com.alibaba.fastjson.JSONObject;
import com.yuebaix.pangu.autoconfigure.common.PanGuStarterConst;
import com.yuebaix.pangu.common.util.JarVersionUtil;
import com.yuebaix.pangu.web.base.BaseReq;
import com.yuebaix.pangu.web.base.BaseResp;
import com.yuebaix.pangu.web.base.Encrypted;
import com.yuebaix.pangu.web.base.PreCheck;
import com.yuebaix.pangu.web.base.RespCodeConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/demo/demo")
public class DemoController {
    @PostMapping(value = "/check", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResp check(@RequestBody BaseReq req) {
        PreCheck.checkArgument(req != null, RespCodeConst.REVIEW);
        //生成公私钥
        String[] pair = Encrypted.genPvtPub();
        String pvt = pair[0];
        String pub = pair[1];

        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(req));
        //客户端加密
        Encrypted<JSONObject> clientEncrypted = Encrypted.clientEncrypt(jsonObject, pub);
        //服务端解密
        JSONObject decrypted = Encrypted.decrypt(clientEncrypted, pvt, JSONObject.class, null);
        //服务端加密
        Encrypted<JSONObject> encrypted = Encrypted.encrypt(jsonObject, pvt);
        //客户端解密
        JSONObject clientDecrypted = Encrypted.clientDecrypt(encrypted, pub, JSONObject.class, null);
        Map<String, Object> result = new HashMap<>();
        result.put("pvt", pvt);
        result.put("pub", pub);
        result.put("clientEncrypted", clientEncrypted);
        result.put("decrypted", decrypted);
        result.put("encrypted", encrypted);
        result.put("clientDecrypted", clientDecrypted);
        return BaseResp.success(result);
    }

    @GetMapping("/version")
    public BaseResp version() {
        String panGuStarterVersion = JarVersionUtil.determine(PanGuStarterConst.class);
        log.info(panGuStarterVersion);
        return BaseResp.success(panGuStarterVersion);
    }
}
