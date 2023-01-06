package com.example.restservice;


import jakarta.persistence.*;

@Entity
@Table(name = "greetings")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    protected Greeting() {
        // Needed for hibernate
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("Greeting[id=%d,content=%s]", id, content);
    }
}
