package com.yuebaix.pangu.test.controller.demo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/test")
public class TestController {
    @ApiOperation("测试")
    @GetMapping("/check")
    public String check() {
        return "success";
    }
}
