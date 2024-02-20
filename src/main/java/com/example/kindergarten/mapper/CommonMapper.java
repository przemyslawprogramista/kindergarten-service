package com.example.kindergarten.mapper;

import com.example.kindergarten.dto.AttendanceDto;
import com.example.kindergarten.dto.ChildDto;
import com.example.kindergarten.dto.ParentDto;
import com.example.kindergarten.dto.SchoolDto;
import com.example.kindergarten.entity.Attendance;
import com.example.kindergarten.entity.Child;
import com.example.kindergarten.entity.Parent;
import com.example.kindergarten.properties.SchoolProperties;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Mapper(componentModel = "spring")
public abstract class CommonMapper {

    @Autowired
    private SchoolProperties properties;

    public abstract ParentDto mapToParentDto(Parent parent);

    public SchoolDto mapToSchoolDto(List<Parent> parents) {
        var parentsDto = mapToParentDtoList(parents);
        var schoolName = parents.get(0).getChildren().stream().findAny().get().getSchool().getName();
        var totalAmount = parentsDto.stream()
                .map(parent -> calcTotalPricePerParent(parent))
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return SchoolDto.builder()
                .name(schoolName)
                .totalAmount(totalAmount)
                .parents(parentsDto)
                .build();
    }

    protected abstract List<ParentDto> mapToParentDtoList(List<Parent> parents);

    @Mapping(target = "allHours", source = "attendance", qualifiedByName = "calcHours")
    @Mapping(target = "allPaidHours", source = "attendance", qualifiedByName = "calcPaidHours")
    protected abstract AttendanceDto mapToAttendanceDto(Attendance attendance);


    @Named("calcHours")
    public int calcHours(Attendance attendance) {
        return attendance.getExitDate().getHour() - attendance.getEntryDate().getHour() + 1;
    }

    @Named("calcPaidHours")
    public long calcPaidHours(Attendance attendance) {
        return IntStream.range(attendance.getEntryDate().getHour(), attendance.getExitDate().getHour() + 1)
                .filter(hour -> hour < properties.getStartFreeHours() || hour >= properties.getEndFreeHours())
                .count();
    }

    @AfterMapping
    public void afterChildMapping(@MappingTarget ChildDto childDto, Child child) {
        BigDecimal hourPrice = child.getSchool().getHourPrice();
        int totalPaidHours = calcTotalPaidHoursPerChild(childDto);

        childDto.setTotalPaidHours(totalPaidHours);
        childDto.setTotalPrice(hourPrice.multiply(BigDecimal.valueOf(totalPaidHours)));
    }

    private int calcTotalPaidHoursPerChild(ChildDto childDto) {
        return childDto.getAttendances().stream()
                .mapToInt(attendance -> attendance.getAllPaidHours())
                .sum();
    }

    @AfterMapping
    public void afterParentMapping(@MappingTarget ParentDto parentDto) {
        BigDecimal totalPricePerParent = parentDto.getChildren().stream()
                .map(x -> x.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        parentDto.setTotalPrice(totalPricePerParent);
    }

    private BigDecimal calcTotalPricePerParent(ParentDto parentDto) {
        return parentDto.getChildren().stream()
                .map(x -> x.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
