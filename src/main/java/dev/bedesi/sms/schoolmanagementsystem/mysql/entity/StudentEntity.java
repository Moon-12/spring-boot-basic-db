package dev.bedesi.sms.schoolmanagementsystem.mysql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name="Student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "roll_no")
    private String rollNo;
    private String name;
    private Boolean active=true;

    @ManyToMany(mappedBy = "studentEntitySet")
    @JsonIgnore
    private Set<CourseEntity> courseEntitySet = new HashSet<>();
}
