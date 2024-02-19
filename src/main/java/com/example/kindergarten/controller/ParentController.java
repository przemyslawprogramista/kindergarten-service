package com.example.kindergarten.controller;

import com.example.kindergarten.dto.AttendanceSummaryDto;
import com.example.kindergarten.service.AttendanceService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("parent")
public class ParentController {

    private final AttendanceService attendanceService;


    @GetMapping
    @RequestMapping("/attendance")
    public ResponseEntity<AttendanceSummaryDto> getAttendancesByParent(@RequestParam long parentId,
                                                                      @RequestParam @Min(1) @Max(12) int month) {
        AttendanceSummaryDto body = attendanceService.getAllAttendancesByParent(parentId, month);
        return ResponseEntity.ok(body);
    }
}
