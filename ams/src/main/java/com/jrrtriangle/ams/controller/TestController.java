package com.jrrtriangle.ams.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/testing")
    public String testing(){
        return "Testing the spring initials by raihan";
    }
}
