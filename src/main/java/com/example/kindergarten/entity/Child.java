package com.example.kindergarten.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    
    private String lastname;

    @OneToMany
    @JoinColumn(name = "child_id")
    private List<Attendance> attendances;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

}
