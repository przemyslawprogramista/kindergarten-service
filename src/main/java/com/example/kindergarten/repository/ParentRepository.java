package com.example.kindergarten.repository;

import com.example.kindergarten.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query(value = "SELECT p FROM Parent p " +
            "JOIN FETCH p.children c " +
            "JOIN FETCH c.school s " +
            "JOIN FETCH c.attendances a " +
            "WHERE p.id = :parentId AND MONTH(a.entryDate) = :month")
    Optional<Parent> findParentByIdAndSpecificMonth(Long parentId, int month);


    @Query("SELECT p FROM Parent p " +
            "JOIN FETCH p.children c " +
            "JOIN FETCH c.school s " +
            "JOIN FETCH c.attendances a " +
            "WHERE s.id = :schoolId AND MONTH(a.entryDate) = :month")
    List<Parent> findParentsBySchoolIdAndSpecificMonth(Long schoolId, int month);
}
