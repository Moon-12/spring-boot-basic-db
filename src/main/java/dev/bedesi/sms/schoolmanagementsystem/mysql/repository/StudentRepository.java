package dev.bedesi.sms.schoolmanagementsystem.mysql.repository;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
    @Query("SELECT s FROM Student s WHERE s.id = :id AND s.active = true")
    Optional<StudentEntity> findById(@Param("id") int id);
}
