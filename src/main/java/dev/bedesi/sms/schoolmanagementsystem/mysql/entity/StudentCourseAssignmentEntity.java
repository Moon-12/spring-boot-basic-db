package dev.bedesi.sms.schoolmanagementsystem.mysql.entity;

import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.StudentCourseRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Std_Course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float marks;
    private boolean active=true;
    @ManyToOne
    @JoinColumn(name="course_id",nullable = false)
    private CourseEntity courseEntity = new CourseEntity();
    @ManyToOne
    @JoinColumn(name="std_id",nullable = false)
    private StudentEntity studentEntity = new StudentEntity();

}


