package dev.bedesi.sms.schoolmanagementsystem.mysql.repository;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, Integer> {
//    @Query("SELECT c FROM Std_Course c WHERE c.id = :id AND c.active = true")
//    Optional<StudentCourseEntity> findById(@Param("id") int id);
    @Query("SELECT c FROM Std_Course c WHERE c.studentId = :std_id AND c.courseId=:course_id AND c.active = true")
    Optional<StudentCourseEntity> findByStudentIdAndCourseId(int std_id, int course_id);
}