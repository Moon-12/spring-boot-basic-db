package dev.bedesi.sms.schoolmanagementsystem.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="Teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Boolean active=true;

    public TeacherEntity(String name) {
        this.name = name;
    }
}