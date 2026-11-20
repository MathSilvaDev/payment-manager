package com.math.payment.infrastructure.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AngularRouteController {

    @GetMapping("/")
    public String forwardToAngular(){
        return "forward:/index.html";
    }
}
