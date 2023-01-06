package com.example.restservice;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "greetings")
@Getter
@NoArgsConstructor
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Greeting[id=%d,content=%s]", id, content);
    }
}
