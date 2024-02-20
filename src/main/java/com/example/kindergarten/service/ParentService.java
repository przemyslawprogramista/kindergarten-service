package com.example.kindergarten.service;

import com.example.kindergarten.dto.ParentDto;
import com.example.kindergarten.dto.SchoolDto;
import com.example.kindergarten.entity.Attendance;
import com.example.kindergarten.entity.Parent;
import com.example.kindergarten.mapper.CommonMapper;
import com.example.kindergarten.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository repository;

    private final CommonMapper mapper;

    @Transactional
    public ParentDto getAllAttendancesByParent(Long parentId, int month) {
        var parent = repository.findParentByIdAndSpecificMonth(parentId, month);

        return mapper.mapToParentDto(parent);
    }

    public SchoolDto getParentsBySchoolIdAndSpecificMonth(Long schoolId, int month) {
        var parents = repository.findParentsBySchoolIdAndSpecificMonth(schoolId, month);

        return mapper.mapToSchoolDto(parents);
    }

}
