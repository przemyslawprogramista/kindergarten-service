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
    void testGetAttendancesBySchoolShouldSuccess() {
        api.perform(get("/school/attendance")
                        .param("schoolId", "2")
                        .param("month", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ZS 2"))
                .andExpect(jsonPath("$.totalAmount").value(38.50))
                .andExpect(jsonPath("$.parents[0].firstname").value("Karol"))
                .andExpect(jsonPath("$.parents[0].lastname").value("Nowak"))
                .andExpect(jsonPath("$.parents[0].totalPrice").value(27.50))
                .andExpect(jsonPath("$.parents[0].children[0].firstname").value("Joanna"))
                .andExpect(jsonPath("$.parents[0].children[0].lastname").value("Nowak"))
                .andExpect(jsonPath("$.parents[0].children[0].totalPaidHours").value(5))
                .andExpect(jsonPath("$.parents[0].children[0].totalPrice").value(27.50))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[0].entryDate").value("2024-02-17T06:12:59"))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[0].exitDate").value("2024-02-17T12:00:00"))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[0].allHours").value(7))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[0].allPaidHours").value(2))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[1].entryDate").value("2024-02-18T05:12:59"))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[1].exitDate").value("2024-02-18T11:01:59"))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[1].allHours").value(7))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[1].allPaidHours").value(2))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[2].entryDate").value("2024-02-18T06:12:59"))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[2].exitDate").value("2024-02-18T11:12:59"))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[2].allHours").value(6))
                .andExpect(jsonPath("$.parents[0].children[0].attendances[2].allPaidHours").value(1))

                .andExpect(jsonPath("$.parents[1].firstname").value("Anna"))
                .andExpect(jsonPath("$.parents[1].lastname").value("Mak"))
                .andExpect(jsonPath("$.parents[1].totalPrice").value(11.00))
                .andExpect(jsonPath("$.parents[1].children[0].firstname").value("Józefa"))
                .andExpect(jsonPath("$.parents[1].children[0].lastname").value("Mak"))
                .andExpect(jsonPath("$.parents[1].children[0].totalPaidHours").value(2))
                .andExpect(jsonPath("$.parents[1].children[0].totalPrice").value(11.00));
    }
    @Test
    @SneakyThrows
    void testGetAttendancesBySchoolShouldNotFound() {
        api.perform(get("/school/attendance")
                        .param("schoolId", "2")
                        .param("month", "4"))
                .andExpect(status().isNotFound());
    }

}