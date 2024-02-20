package com.example.kindergarten.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    @OneToMany
    @JoinColumn(name = "parent_id")
    @OrderBy("firstname ASC")
    private Set<Child> children = new LinkedHashSet<>();
}
