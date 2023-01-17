package com.example;

import com.example.restservice.Greeting;
import com.example.restservice.GreetingRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingRepositoryTests {

    @Autowired
    private GreetingRepository greetingRepository;

    @Test
    public void whenFindingCustomerById_thenCorrect() {
        Greeting greeting = greetingRepository.save(new Greeting("John"));
        assertTrue(greetingRepository.findById(greeting.getId()).isPresent());
        assertEquals(greetingRepository.findById(greeting.getId()).get().getContent(), "John");
    }

    @Test
    public void whenFindingAllCustomers_thenCorrect() {
        Greeting greeting1 = greetingRepository.save(new Greeting("John"));
        Greeting greeting2 = greetingRepository.save(new Greeting("Julie"));
        assertTrue(greetingRepository.findAll().iterator().hasNext());
        assertNotEquals(greeting1.getId(), 0);
        assertNotEquals(greeting2.getId(), 0);
        assertNotEquals(greeting1.getId(), greeting2.getId());
    }
}