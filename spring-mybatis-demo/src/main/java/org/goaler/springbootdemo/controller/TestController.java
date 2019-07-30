package org.goaler.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("print")
    public String print(){
        System.out.println("print success");
        return "success";
    }

    @PostMapping("hello")
    public String hello(){
        System.out.println("hello post");
        return "hello";
    }

    @PostMapping("/postTest")
    public String postTest(){
        System.out.println("post success");
        return "post success";
    }
}
