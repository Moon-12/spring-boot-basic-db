package dev.bedesi.sms.schoolmanagementsystem.DTO;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseAssignmentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentCourseDTO implements TranslateDTO<StudentCourseAssignmentEntity>{
    private int id;

    @Override
    public void setAllFieldsFromEntity(StudentCourseAssignmentEntity entity) {
        this.id= entity.getId();
    }
}
