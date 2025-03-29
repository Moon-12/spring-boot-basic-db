package dev.bedesi.sms.schoolmanagementsystem.mysql.repository;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
    @Query("SELECT t FROM Teacher t WHERE t.id = :id AND t.active = true")
    Optional<TeacherEntity> findById(@Param("id") int id);
}