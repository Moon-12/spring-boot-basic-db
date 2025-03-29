package dev.bedesi.sms.schoolmanagementsystem.DTO;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements TranslateDTO<StudentEntity>{
    private int id;
    private String rollNo;
    private String name;
    private Boolean active;

    @Override
    public void setAllFieldsFromEntity(StudentEntity entity) {
        this.id=entity.getId();
        this.rollNo=entity.getRollNo();
        this.name=entity.getName();
        this.active=entity.getActive();
    }


}
