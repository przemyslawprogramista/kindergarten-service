package com.example.kindergarten.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class SchoolDto {

    private String name;

    private BigDecimal totalPrice;

    private List<ParentDto> parents;

}
