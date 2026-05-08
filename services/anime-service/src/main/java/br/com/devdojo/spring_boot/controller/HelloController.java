package br.com.devdojo.spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //@RequestMapping(method = RequestMethod.GET, value = "hello")
    @GetMapping("hello")
    public String sayHello() {
        return "Hello World!";
    }
}
