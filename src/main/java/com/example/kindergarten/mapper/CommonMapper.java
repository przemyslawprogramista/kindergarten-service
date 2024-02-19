package com.example.kindergarten.mapper;

import com.example.kindergarten.domain.Attendance;
import com.example.kindergarten.domain.Child;
import com.example.kindergarten.dto.AttendanceDto;
import com.example.kindergarten.dto.AttendanceSummaryDto;
import com.example.kindergarten.dto.ChildDto;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    AttendanceSummaryDto mapToAttendanceSummaryDto(List<Attendance> attendances, BigDecimal totalPrice);

    List<AttendanceDto> mapToAttendanceDtoList(List<Attendance> attendances);

    AttendanceDto mapAttendanceDto(Attendance attendance);

    ChildDto mapChildDto(Child child);

}
