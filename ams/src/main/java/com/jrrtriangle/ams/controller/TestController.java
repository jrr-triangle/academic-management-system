package com.jrrtriangle.ams.controller;

import org.springframework.web.bind.annotation.*;

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


    @DeleteMapping("/testing/deleting")
    public String deleting(){
        return "Testing deleting  the spring initials by raihan";
    }

    @PutMapping("/testing/editing")
    public String editing(){
        return "Testing editing  the spring initials by raihan";
    }

    @PostMapping("/testing/creating")
    public String creating(){
        return "Testing creating  the spring initials by raihan";
    }

    @GetMapping("/testing/view")
    public String view(){
        return "Testing view  the spring initials by raihan";
    }
    @GetMapping("/testing/param")
    public String viewparam(@RequestParam("p") String p){
        return "Testing view  the spring initials by raihan: "+p;
    }
    @GetMapping("/testing/param?p={p}andq={q}")
    public String viewparammore(@RequestParam("p") String p,@RequestParam("q") String q){
        return "Testing view  the spring initials by raihan: "+p+" and "+q;
    }
    @PutMapping("/testing/update/{id}")
    public String update(@PathVariable String id){
        return "Testing editing  the" +
                " spring initials by raihan ID: "+id;
    }

    @PutMapping("/testing/update/{id}/{age}")
    public String update(@PathVariable String id,@PathVariable int age){
        return "Testing editing  the" +
                " spring initials by raihan ID: "+id+" age: "+age;
    }
}
