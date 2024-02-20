package com.example.kindergarten.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OrderColumn
    private String firstname;
    
    private String lastname;

    @OneToMany
    @JoinColumn(name = "child_id")
    @OrderBy("entryDate ASC")
    private Set<Attendance> attendances = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

}
