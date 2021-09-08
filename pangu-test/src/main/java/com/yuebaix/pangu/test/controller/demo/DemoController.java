package com.yuebaix.pangu.test.controller.demo;

import com.yuebaix.pangu.web.base.BaseReq;
import com.yuebaix.pangu.web.base.BaseResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/demo/demo")
public class DemoController {
    @ApiOperation("示例")
    @PostMapping(value = "/check", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResp check(@RequestBody BaseReq req) {
        return BaseResp.success(Collections.singletonMap("say", "demo"));
    }
}
