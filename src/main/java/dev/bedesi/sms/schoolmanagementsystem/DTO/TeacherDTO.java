package dev.bedesi.sms.schoolmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TeacherDTO {
    private int id;
    public TeacherDTO(int id){
        this.id=id;
    }
}
