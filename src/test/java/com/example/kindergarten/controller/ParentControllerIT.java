package com.example.kindergarten.controller;

import com.example.kindergarten.MySqlContainerBaseIT;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ParentControllerIT extends MySqlContainerBaseIT {

    @Autowired
    private MockMvc api;

    @Test
    @SneakyThrows
    void testGetAttendancesByParentShouldSuccess() {
        api.perform(get("/parent/attendance")
                        .param("parentId", "3")
                        .param("month", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("Anna"))
                .andExpect(jsonPath("$.lastname").value("Mak"))
                .andExpect(jsonPath("$.totalPrice").value(13.29))
                .andExpect(jsonPath("$.children[0].firstname").value("Amelia"))
                .andExpect(jsonPath("$.children[0].lastname").value("Mak"))
                .andExpect(jsonPath("$.children[0].totalPaidHours").value(1))
                .andExpect(jsonPath("$.children[0].totalPrice").value(7.79))
                .andExpect(jsonPath("$.children[0].attendances[0].entryDate").value("2024-01-17T08:12:59"))
                .andExpect(jsonPath("$.children[0].attendances[0].exitDate").value("2024-01-17T12:01:00"))
                .andExpect(jsonPath("$.children[0].attendances[0].allHours").value(5))
                .andExpect(jsonPath("$.children[0].attendances[0].allPaidHours").value(1))
                .andExpect(jsonPath("$.children[0].attendances[1].entryDate").value("2024-01-18T09:12:59"))
                .andExpect(jsonPath("$.children[0].attendances[1].exitDate").value("2024-01-18T11:12:59"))
                .andExpect(jsonPath("$.children[0].attendances[1].allHours").value(3))
                .andExpect(jsonPath("$.children[0].attendances[1].allPaidHours").value(0))

                .andExpect(jsonPath("$.children[1].firstname").value("Józefa"))
                .andExpect(jsonPath("$.children[1].lastname").value("Mak"))
                .andExpect(jsonPath("$.children[1].totalPaidHours").value(1))
                .andExpect(jsonPath("$.children[1].totalPrice").value(5.50))
                .andExpect(jsonPath("$.children[1].attendances[0].entryDate").value("2024-01-17T08:12:59"))
                .andExpect(jsonPath("$.children[1].attendances[0].exitDate").value("2024-01-17T12:01:00"))
                .andExpect(jsonPath("$.children[1].attendances[0].allHours").value(5))
                .andExpect(jsonPath("$.children[1].attendances[0].allPaidHours").value(1))
                .andExpect(jsonPath("$.children[1].attendances[1].entryDate").value("2024-01-18T09:12:59"))
                .andExpect(jsonPath("$.children[1].attendances[1].exitDate").value("2024-01-18T11:12:59"))
                .andExpect(jsonPath("$.children[1].attendances[1].allHours").value(3))
                .andExpect(jsonPath("$.children[1].attendances[1].allPaidHours").value(0));
    }

    @Test
    @SneakyThrows
    void testGetAttendancesByParentShould404() {
        api.perform(get("/parent/attendance")
                        .param("parentId", "3")
                        .param("month", "5"))
                .andExpect(status().isNotFound());
    }
}