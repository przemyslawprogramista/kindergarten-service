package com.example.kindergarten.controller;

import com.example.kindergarten.dto.ParentDto;
import com.example.kindergarten.service.ParentService;
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

    private final ParentService parentService;


    @GetMapping
    @RequestMapping("/attendance")
    public ResponseEntity<ParentDto> getAttendancesByParent(@RequestParam long parentId,
                                                            @RequestParam @Min(1) @Max(12) int month) {
        ParentDto body = parentService.getAllAttendancesByParent(parentId, month);
        return ResponseEntity.ok(body);
    }
}
