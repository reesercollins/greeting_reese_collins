package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final String template = "Hello, %s";

    GreetingRepository greetingRepository;

    @Autowired
    public void setRepository(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @GetMapping
    public ResponseEntity<List<Greeting>> getGreetings() {
        List<Greeting> result = new ArrayList<>();
        greetingRepository.findAll().forEach(result::add);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting greeting = new Greeting(String.format(template, name));
        greetingRepository.save(greeting);
        return new ResponseEntity<>(greeting, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Greeting> editGreeting(@RequestParam(name = "id") long id, @RequestBody GreetingEditRequestModel editRequestModel) {
        Optional<Greeting> opt_greeting = greetingRepository.findById(id);
        if (opt_greeting.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Greeting greeting = opt_greeting.get();
        greeting.setContent(editRequestModel.getContent());
        greetingRepository.save(greeting);
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

}
