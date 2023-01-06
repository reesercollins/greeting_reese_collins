package com.example;

import com.example.restservice.Greeting;
import com.example.restservice.GreetingRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingRepositoryTests {

    @Autowired
    private GreetingRepository greetingRepository;

    @Test
    public void whenFindingCustomerById_thenCorrect() {
        greetingRepository.save(new Greeting("John"));
        assertTrue(greetingRepository.findById(1L).isPresent());
        assertEquals(greetingRepository.findById(1L).get().getContent(), "John");
    }

    @Test
    public void whenFindingAllCustomers_thenCorrect() {
        greetingRepository.save(new Greeting("John"));
        greetingRepository.save(new Greeting("Julie"));
        assertTrue(greetingRepository.findAll().iterator().hasNext());
    }
}