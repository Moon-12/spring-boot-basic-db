package dev.bedesi.sms.schoolmanagementsystem.mysql.repository;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface StudentCourseRepository extends JpaRepository<StudentCourseAssignmentEntity, Integer> {
//    @Query("SELECT c FROM Std_Course c WHERE c.id = :id AND c.active = true")
//    Optional<StudentCourseEntity> findById(@Param("id") int id);
    @Query("SELECT c FROM Std_Course c WHERE c.studentEntity.id = :std_id AND c.courseEntity.id=:course_id AND c.active = true")
    Optional<StudentCourseAssignmentEntity> findByStudentIdAndCourseId(int std_id, int course_id);
}