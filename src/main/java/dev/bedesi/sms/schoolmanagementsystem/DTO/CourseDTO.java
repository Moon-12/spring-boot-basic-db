package dev.bedesi.sms.schoolmanagementsystem.DTO;


import lombok.Data;

@Data
public class CourseDTO {
    private int id;
    private String name;
    private boolean active;
    public CourseDTO(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }
}
