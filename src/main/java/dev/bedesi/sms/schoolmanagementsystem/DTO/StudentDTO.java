package dev.bedesi.sms.schoolmanagementsystem.DTO;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import lombok.Data;

@Data
public class StudentDTO implements TranslateDTO<StudentEntity>{
    private int id;
    private String rollNo;
    private String name;

    @Override
    public void setAllFieldsFromEntity(StudentEntity entity) {
        this.id=entity.getId();
        this.rollNo=entity.getRollNo();
        this.name=entity.getName();
    }


}
