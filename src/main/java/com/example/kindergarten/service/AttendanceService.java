package com.example.kindergarten.service;

import com.example.kindergarten.domain.Attendance;
import com.example.kindergarten.dto.AttendanceSummaryDto;
import com.example.kindergarten.mapper.CommonMapper;
import com.example.kindergarten.repository.AttendanceRepository;
import com.example.kindergarten.util.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository repository;

    private final CommonMapper mapper;

    private final PriceCalculator priceCalculator;

    public AttendanceSummaryDto getAllAttendancesByParent(Long parentId, int month) {
        List<Attendance> allAttendanceByParent = repository.findAllAttendanceByParent(parentId, month);
        BigDecimal totalPrice = calcTotalPrice(allAttendanceByParent);

        return mapper.mapToAttendanceSummaryDto(allAttendanceByParent, totalPrice);
    }

    public AttendanceSummaryDto getAllAttendancesBySchool(Long schoolId, int month) {
        List<Attendance> allAttendanceBySchool = repository.findAllAttendanceBySchool(schoolId, month);
        BigDecimal totalPrice = calcTotalPrice(allAttendanceBySchool);

        return mapper.mapToAttendanceSummaryDto(allAttendanceBySchool, totalPrice);
    }

    private BigDecimal calcTotalPrice(List<Attendance> attendances) {
        return attendances.stream()
                .map(attendance -> priceCalculator.calcPricePerDay(
                        attendance.getEntryDate(),
                        attendance.getExitDate(),
                        attendance.getChild().getSchool().getHourPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
