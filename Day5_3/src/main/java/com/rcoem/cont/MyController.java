package com.rcoem.cont;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/home")
    public String show(){
        return "welcome to session";
    }
}
