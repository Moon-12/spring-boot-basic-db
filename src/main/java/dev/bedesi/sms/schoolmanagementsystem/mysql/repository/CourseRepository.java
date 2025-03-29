package dev.bedesi.sms.schoolmanagementsystem.mysql.repository;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.CourseEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    @Query("SELECT c FROM Course c WHERE c.id = :id AND c.active = true")
    Optional<CourseEntity> findById(@Param("id") int id);
}