package com.example.kindergarten.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AttendanceSummaryDto {
    private List<AttendanceDto> attendances;
    private BigDecimal totalPrice;
}
