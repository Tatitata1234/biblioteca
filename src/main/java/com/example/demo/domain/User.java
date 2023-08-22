package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Long id;
    private String name;

    private int age;

    @ManyToOne
    private Address address;

    private boolean active;

    @ManyToMany
    private List<Book> livrosAlugados = new ArrayList<>();

    @ManyToMany(fetch = LAZY)
    private List<Book> livrosLidos;
}
