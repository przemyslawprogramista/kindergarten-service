package com.example.kindergarten.repository;

import com.example.kindergarten.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("SELECT p FROM Parent p " +
            "JOIN p.children c " +
            "JOIN c.attendances a " +
            "WHERE p.id = :parentId AND a.exitDate = :month")
    Parent findParentByIdAndSpecificMonth(Long parentId, int month);

    @Query("SELECT p FROM Parent p " +
            "JOIN p.children c " +
            "JOIN c.school s " +
            "JOIN c.attendances a " +
            "WHERE s.id = :schoolId AND MONTH(a.entryDate) = :month")
    List<Parent> findParentsBySchoolIdAndSpecificMonth(Long schoolId, int month);
}
