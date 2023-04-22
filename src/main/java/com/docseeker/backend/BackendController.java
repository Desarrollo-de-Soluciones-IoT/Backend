package com.docseeker.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World from DocSeeker Backend Application!";
    }
}
