package com.yuebaix.pangu.test.controller.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/test")
public class TestController {

    @GetMapping("/check")
    public String check() {
        return "success";
    }
}
