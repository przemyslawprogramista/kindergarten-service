package com.example.kindergarten.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceDto {

    private LocalDateTime entryDate;

    private LocalDateTime exitDate;

    private int allHours;

    private int allPaidHours;
}
