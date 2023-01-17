package com.example.restservice;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Audited
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "greeting_generator")
    @SequenceGenerator(name = "greeting_generator", sequenceName = "greeting_seq", allocationSize = 1)
    private long id;

    @NonNull
    @Setter
    private String content;

}
