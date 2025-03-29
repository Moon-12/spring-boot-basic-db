package dev.bedesi.sms.schoolmanagementsystem.DTO;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String rollNo;
    private String name;

    public StudentDTO(int id, String rollNo,String name ) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
    }
}
