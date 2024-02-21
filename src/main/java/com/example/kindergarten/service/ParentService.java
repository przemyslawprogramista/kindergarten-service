package com.example.kindergarten.service;

import com.example.kindergarten.dto.ParentDto;
import com.example.kindergarten.dto.SchoolDto;
import com.example.kindergarten.exception.NotFoundException;
import com.example.kindergarten.mapper.CommonMapper;
import com.example.kindergarten.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository repository;

    private final CommonMapper mapper;

    public ParentDto getAllAttendancesByParent(Long parentId, int month) {
        var parent = repository.findParentByIdAndSpecificMonth(parentId, month)
                .orElseThrow(() -> new NotFoundException(String.format("Not found attendances for parentId: %s and month %s", parentId, month)));

        return mapper.mapToParentDto(parent);
    }

    public SchoolDto getParentsBySchoolIdAndSpecificMonth(Long schoolId, int month) {
        var parents = repository.findParentsBySchoolIdAndSpecificMonth(schoolId, month);

        if (parents.size() == 0)
            throw new NotFoundException(String.format("Not found attendances for schoolId: %s and month %s", schoolId, month));

        return mapper.mapToSchoolDto(parents);
    }

}
