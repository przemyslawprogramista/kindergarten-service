package com.example.kindergarten.repository;

import com.example.kindergarten.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a " +
            "JOIN a.child c " +
            "JOIN c.parent p " +
            "WHERE p.id = :parentId AND MONTH(a.entryDate) = :month")
    List<Attendance> findAllAttendanceByParent(Long parentId, int month);

    @Query("SELECT a FROM Attendance a " +
            "JOIN a.child c " +
            "JOIN c.school s " +
            "WHERE s.id = :schoolId AND MONTH(a.entryDate) = :month")
    List<Attendance> findAllAttendanceBySchool(Long schoolId, int month);
}
