package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@Controller
@ResponseBody
@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @GetMapping("/")
    Map<String, String> hello(Principal principal) {
//        var name = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getName();
        var name = principal.getName();
        return Map.of("message", "hello, " + name + "!");
    }

}
