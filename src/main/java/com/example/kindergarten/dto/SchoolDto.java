package com.example.kindergarten.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class SchoolDto {

    private String name;

    private BigDecimal totalAmount;

    private List<ParentDto> parents;

}
