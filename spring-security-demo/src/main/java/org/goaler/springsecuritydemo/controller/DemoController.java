package org.goaler.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("print")
    public String print(){
        return "success";
    }

    @PostMapping("hello")
    public String hello(){
        return "hello";
    }
}
