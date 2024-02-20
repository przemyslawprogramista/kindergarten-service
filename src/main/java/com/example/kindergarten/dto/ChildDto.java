package com.example.kindergarten.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ChildDto {

    private String firstname;
    
    private String lastname;

    private int totalPaidHours;

    private BigDecimal totalPrice;

    private List<AttendanceDto> attendances;

}
