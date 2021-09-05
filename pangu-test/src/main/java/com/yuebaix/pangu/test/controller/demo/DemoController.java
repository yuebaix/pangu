package com.yuebaix.pangu.test.controller.demo;

import com.yuebaix.pangu.common.util.JacksonUtil;
import com.yuebaix.pangu.web.base.BaseReq;
import com.yuebaix.pangu.web.base.BaseResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/demo/demo")
public class DemoController {
    @ApiOperation("示例")
    @GetMapping("/check")
    public BaseResp check(BaseReq req) {
        return BaseResp.success(JacksonUtil.write(Collections.singletonMap("say", "demo")));
    }
}
