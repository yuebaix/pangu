package com.yuebaix.pangu.test.controller.demo;

import com.yuebaix.pangu.web.base.BaseReq;
import com.yuebaix.pangu.web.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/demo/test")
public class TestController {

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @GetMapping("/check")
    public BaseResp check(BaseReq req) {
        return BaseResp.success(Collections.singletonMap("say", "test"));
    }
}
