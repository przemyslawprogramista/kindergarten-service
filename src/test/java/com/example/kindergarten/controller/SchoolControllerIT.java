package com.example.kindergarten.controller;

import com.example.kindergarten.MySqlContainerBaseIT;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SchoolControllerIT extends MySqlContainerBaseIT {

    @Autowired
    private MockMvc api;

    @Test
    @SneakyThrows
    void testGetAttendancesBySchool() {
        api.perform(get("/school/attendance")
                        .param("schoolId", "2")
                        .param("month", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.attendances[0].child.firstname").value("Joanna"))
                .andExpect(jsonPath("$.attendances[0].child.lastname").value("Nowak"))
                .andExpect(jsonPath("$.attendances[0].entryDate").value("2024-02-18T05:12:59"))
                .andExpect(jsonPath("$.attendances[0].exitDate").value("2024-02-18T11:01:59"))
                .andExpect(jsonPath("$.attendances[1].child.firstname").value("Joanna"))
                .andExpect(jsonPath("$.attendances[1].child.lastname").value("Nowak"))
                .andExpect(jsonPath("$.attendances[1].entryDate").value("2024-02-17T06:12:59"))
                .andExpect(jsonPath("$.attendances[1].exitDate").value("2024-02-17T12:00:00"))
                .andExpect(jsonPath("$.attendances[2].child.firstname").value("Joanna"))
                .andExpect(jsonPath("$.attendances[2].child.lastname").value("Nowak"))
                .andExpect(jsonPath("$.attendances[2].entryDate").value("2024-02-18T06:12:59"))
                .andExpect(jsonPath("$.attendances[2].exitDate").value("2024-02-18T11:12:59"))
                .andExpect(jsonPath("$.attendances[3].child.firstname").value("Józefa"))
                .andExpect(jsonPath("$.attendances[3].child.lastname").value("Mak"))
                .andExpect(jsonPath("$.attendances[3].entryDate").value("2024-02-18T05:12:59"))
                .andExpect(jsonPath("$.attendances[3].exitDate").value("2024-02-18T11:01:59"))
                .andExpect(jsonPath("$.totalPrice").value(38.50));
    }

}