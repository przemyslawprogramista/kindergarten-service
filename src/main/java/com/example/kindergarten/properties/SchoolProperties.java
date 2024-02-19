package com.example.kindergarten.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "school")
public class SchoolProperties {

    private int startFreeHours;

    private int endFreeHours;
}
