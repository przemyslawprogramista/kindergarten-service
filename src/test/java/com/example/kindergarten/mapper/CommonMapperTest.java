package com.example.kindergarten.mapper;

import com.example.kindergarten.MapperMockFactory;
import com.example.kindergarten.properties.SchoolProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CommonMapperTest {

    @Mock
    private SchoolProperties schoolProperties;

    @InjectMocks
    private final CommonMapper mapper = Mappers.getMapper(CommonMapper.class);

    @Test
    void mapToSchoolDtoTest() {
        Mockito.when(schoolProperties.getStartFreeHours()).thenReturn(7);
        Mockito.when(schoolProperties.getEndFreeHours()).thenReturn(12);
        var payload = MapperMockFactory.getListParentsMock();

        var actual = mapper.mapToSchoolDto(payload);

        assertEquals("ZS1", actual.getName());
        assertEquals(0, BigDecimal.valueOf(186.66).compareTo(actual.getTotalAmount()));
        assertEquals(2, actual.getParents().size());
        assertEquals("Anna", actual.getParents().get(0).getFirstname());
        assertEquals("Lewandowska", actual.getParents().get(0).getLastname());
        assertEquals(0, BigDecimal.valueOf(20.74).compareTo(actual.getParents().get(0).getTotalPrice()));
        assertEquals("Jan", actual.getParents().get(0).getChildren().get(0).getFirstname());
        assertEquals("Lewandowski", actual.getParents().get(0).getChildren().get(0).getLastname());
        assertEquals(LocalDateTime.parse("2024-01-01T06:59", DateTimeFormatter.ISO_DATE_TIME),
                actual.getParents().get(0).getChildren().get(0).getAttendances().get(0).getEntryDate());
        assertEquals(LocalDateTime.parse("2024-01-01T12:01", DateTimeFormatter.ISO_DATE_TIME),
                actual.getParents().get(0).getChildren().get(0).getAttendances().get(0).getExitDate());
        assertEquals(7, actual.getParents().get(0).getChildren().get(0).getAttendances().get(0).getAllHours());
        assertEquals(2, actual.getParents().get(0).getChildren().get(0).getAttendances().get(0).getAllPaidHours());
        assertEquals(LocalDateTime.parse("2024-01-01T07:00", DateTimeFormatter.ISO_DATE_TIME),
                actual.getParents().get(0).getChildren().get(0).getAttendances().get(1).getEntryDate());
        assertEquals(LocalDateTime.parse("2024-01-01T11:59", DateTimeFormatter.ISO_DATE_TIME),
                actual.getParents().get(0).getChildren().get(0).getAttendances().get(1).getExitDate());
        assertEquals(5, actual.getParents().get(0).getChildren().get(0).getAttendances().get(1).getAllHours());
        assertEquals(0, actual.getParents().get(0).getChildren().get(0).getAttendances().get(1).getAllPaidHours());
        assertEquals(2, actual.getParents().get(1).getChildren().size());
        assertEquals(2, actual.getParents().get(1).getChildren().get(0).getAttendances().size());
        assertEquals(2, actual.getParents().get(1).getChildren().get(1).getAttendances().size());
    }

}