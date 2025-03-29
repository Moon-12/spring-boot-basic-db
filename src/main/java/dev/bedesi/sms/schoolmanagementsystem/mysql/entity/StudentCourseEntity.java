package dev.bedesi.sms.schoolmanagementsystem.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Std_Course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float marks;
    private boolean active=true;
    @Column(name="std_id")
    private int studentId;
    @Column(name="course_id")
    private int courseId;
}
