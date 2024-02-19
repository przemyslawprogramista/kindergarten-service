package com.example.kindergarten.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;

    @Column(name = "exit_date", nullable = false)
    private LocalDateTime exitDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "child_id", nullable = false)
    private Child child;
}
