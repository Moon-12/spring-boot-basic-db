package dev.bedesi.sms.schoolmanagementsystem.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Boolean active=true;
    @OneToOne
    @JoinColumn(name="teach_id")
    private TeacherEntity teacher;

    @ManyToMany()
    @JoinTable(
            name = "STD_COURSE",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "std_id")
    )
    private Set<StudentEntity> studentEntitySet = new HashSet<>();
}