package com.jrrtriangle.ams.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/testing")
    public String testing(){
        return "Testing the spring initials by raihan";
    }

    @GetMapping("/testing2")
    public String testing2(){
        return "Testing2 the spring initials by raihan";
    }


    @GetMapping("/testing3")
    public String testing3(){
        return "Testing3 the spring initials by raihan";
    }


    @GetMapping("/testing4")
    public String testing4(){
        return "Testing4  the spring initials by raihan";
    }
}
