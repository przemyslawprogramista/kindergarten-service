package com.example.kindergarten.util;

import com.example.kindergarten.properties.SchoolProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class PriceCalculator {

    private final SchoolProperties properties;

    public BigDecimal calcPricePerDay(LocalDateTime from, LocalDateTime to, BigDecimal price) {
        long countHours = IntStream.range(from.getHour(), to.getHour() + 1)
                .filter(hour -> hour < properties.getStartFreeHours() || hour >= properties.getEndFreeHours())
                .count();

        return price.multiply(BigDecimal.valueOf(countHours));
    }
}
