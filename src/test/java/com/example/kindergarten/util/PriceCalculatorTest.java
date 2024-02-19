package com.example.kindergarten.util;

import com.example.kindergarten.properties.SchoolProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PriceCalculatorTest {

    @Mock
    private SchoolProperties schoolProperties;

    @InjectMocks
    private PriceCalculator priceCalculator;

    @ParameterizedTest
    @MethodSource
    void calcPricePerDay(LocalDateTime from, LocalDateTime to, BigDecimal price, BigDecimal expectedPrice) {
        //given
        Mockito.when(schoolProperties.getStartFreeHours()).thenReturn(7);
        Mockito.when(schoolProperties.getEndFreeHours()).thenReturn(12);

        //when
        BigDecimal actual = priceCalculator.calcPricePerDay(from, to, price);

        //then
        assertEquals(expectedPrice, actual);
    }

    private static Stream<Arguments> calcPricePerDay() {
        return Stream.of(
                Arguments.of(
                        LocalDateTime.of(2024, 1, 1, 7, 1),
                        LocalDateTime.of(2024, 1, 1, 12, 1),
                        BigDecimal.ONE,
                        BigDecimal.ONE),
                Arguments.of(
                        LocalDateTime.of(2024, 1, 1, 6, 59),
                        LocalDateTime.of(2024, 1, 1, 11, 59),
                        BigDecimal.ONE,
                        BigDecimal.ONE),
                Arguments.of(
                        LocalDateTime.of(2024, 1, 1, 7, 0),
                        LocalDateTime.of(2024, 1, 1, 11, 59),
                        BigDecimal.ONE,
                        BigDecimal.ZERO),
                Arguments.of(
                        LocalDateTime.of(2024, 1, 1, 7, 1),
                        LocalDateTime.of(2024, 1, 1, 11, 59),
                        BigDecimal.ONE,
                        BigDecimal.ZERO),
                Arguments.of(
                        LocalDateTime.of(2024, 1, 1, 6, 1),
                        LocalDateTime.of(2024, 1, 1, 14, 1),
                        BigDecimal.ONE,
                        BigDecimal.valueOf(4))
        );
    }
}