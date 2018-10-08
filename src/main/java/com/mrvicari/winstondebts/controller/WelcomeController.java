package com.mrvicari.winstondebts.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class WelcomeController {

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "wagwan";
    }
}
