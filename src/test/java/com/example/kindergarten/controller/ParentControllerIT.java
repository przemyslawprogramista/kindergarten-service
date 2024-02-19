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
    void testGetAttendancesByParent() {
        api.perform(get("/parent/attendance")
                        .param("parentId", "3")
                        .param("month", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.attendances[0].child.firstname").value("Amelia"))
                .andExpect(jsonPath("$.attendances[0].child.lastname").value("Mak"))
                .andExpect(jsonPath("$.attendances[0].entryDate").value("2024-01-17T08:12:59"))
                .andExpect(jsonPath("$.attendances[0].exitDate").value("2024-01-17T12:01:00"))
                .andExpect(jsonPath("$.attendances[1].child.firstname").value("Amelia"))
                .andExpect(jsonPath("$.attendances[1].child.lastname").value("Mak"))
                .andExpect(jsonPath("$.attendances[1].entryDate").value("2024-01-18T09:12:59"))
                .andExpect(jsonPath("$.attendances[1].exitDate").value("2024-01-18T11:12:59"))
                .andExpect(jsonPath("$.attendances[2].child.firstname").value("Józefa"))
                .andExpect(jsonPath("$.attendances[2].child.lastname").value("Mak"))
                .andExpect(jsonPath("$.attendances[2].entryDate").value("2024-01-17T08:12:59"))
                .andExpect(jsonPath("$.attendances[2].exitDate").value("2024-01-17T12:01:00"))
                .andExpect(jsonPath("$.attendances[3].child.firstname").value("Józefa"))
                .andExpect(jsonPath("$.attendances[3].child.lastname").value("Mak"))
                .andExpect(jsonPath("$.attendances[3].entryDate").value("2024-01-18T09:12:59"))
                .andExpect(jsonPath("$.attendances[3].exitDate").value("2024-01-18T11:12:59"))
                .andExpect(jsonPath("$.totalPrice").value(13.29));
    }
}