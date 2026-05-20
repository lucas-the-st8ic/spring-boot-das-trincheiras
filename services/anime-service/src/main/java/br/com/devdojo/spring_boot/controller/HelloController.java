package br.com.devdojo.spring_boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController()
@RequestMapping("v1/hello")
@Slf4j
public class HelloController {

    //@RequestMapping(method = RequestMethod.GET, value = "hello")
    @GetMapping
    public String sayHello() {
        return "Hello World!";
    }

    @PostMapping
    public Long save(@RequestBody String name) {
        log.info("Save {}", name);
        return ThreadLocalRandom.current()
                .nextLong(1, 1000);
    }
}
