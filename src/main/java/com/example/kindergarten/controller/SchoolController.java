package com.example.kindergarten.controller;

import com.example.kindergarten.dto.SchoolDto;
import com.example.kindergarten.service.ParentService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("school")
public class SchoolController {

    private final ParentService parentService;

    @GetMapping
    @RequestMapping("/attendance")
    public ResponseEntity<SchoolDto> getAttendancesBySchool(@RequestParam long schoolId,
                                                            @RequestParam @Min(1) @Max(12) int month) {
        return ResponseEntity.ok(parentService.getParentsBySchoolIdAndSpecificMonth(schoolId, month));
    }
}
