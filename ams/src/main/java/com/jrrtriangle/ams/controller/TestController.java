package com.jrrtriangle.ams.controller;

import com.jrrtriangle.ams.entity.Endpoint;
import com.jrrtriangle.ams.service.AuthorityService;
import com.jrrtriangle.ams.service.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    final EndpointService endpointService;


    public TestController(EndpointService endpointService) {
        this.endpointService = endpointService;

    }

    @GetMapping("/testing")
    @PreAuthorize("hasAnyAuthority(@authservice.getAuthority('testing'))")
    public String testing(){
        return "Testing the spring initials by raihan";
    }

    @GetMapping("/testing2")
    @PreAuthorize("hasAnyAuthority(@authservice.getAuthority('testing2'))")
    public String testing2(){
        return "Testing the spring initials by raihan";
    }

    @GetMapping("/testing3")
    @PreAuthorize("hasAnyAuthority(@authservice.getAuthority('testing3'))")
    public String testing3(){
        return "Testing the spring initials by raihan";
    }

    @GetMapping("/testing4")
    @PreAuthorize("hasAnyAuthority(@authservice.getAuthority('testing4'))")
    public String testing4(){
        return "Testing the spring initials by raihan 4";
    }


    @PostMapping(path="/addupdateendpoint",consumes = "application/json", produces = "application/json")
    public Endpoint addUpdateEndpoint(@RequestBody Endpoint endpoint){
        return endpointService.addUpdateEndpoint(endpoint);

    }


}
