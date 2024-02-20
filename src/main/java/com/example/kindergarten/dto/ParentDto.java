package com.example.kindergarten.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ParentDto {

    private String firstname;
    
    private String lastname;

    private BigDecimal totalPrice;

    private List<ChildDto> children;

}
