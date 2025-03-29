package dev.bedesi.sms.schoolmanagementsystem.DTO;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.TeacherEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentCourseDTO implements TranslateDTO<StudentCourseEntity>{
    private int id;

    @Override
    public void setAllFieldsFromEntity(StudentCourseEntity entity) {
        this.id= entity.getId();
    }
}
