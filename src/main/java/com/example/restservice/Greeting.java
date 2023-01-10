package com.example.restservice;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "greeting")
@Getter
@NoArgsConstructor
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "greeting_generator")
    @SequenceGenerator(name = "greeting_generator", sequenceName = "greeting_seq", allocationSize = 1)
    private long id;
    @Setter
    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Greeting[id=%d,content=%s]", id, content);
    }
}
