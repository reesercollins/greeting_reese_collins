package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s";

    GreetingRepository greetingRepository;

    @Autowired
    public void setRepository(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @GetMapping("/greeting")
    public List<Greeting> getGreetings() {
        List<Greeting> result = new ArrayList<>();
        greetingRepository.findAll().forEach(result::add);
        return result;
    }

    @PostMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting greeting = new Greeting(String.format(template, name));
        greetingRepository.save(greeting);
        return greeting;
    }

}
