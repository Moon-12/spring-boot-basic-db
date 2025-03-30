package dev.bedesi.sms.schoolmanagementsystem.DTO;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseAssignmentEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentCourseAssignmentDTO implements TranslateDTO<StudentCourseAssignmentEntity>{
    private int id;
    private float marks;
    private StudentDTO studentDTO=new StudentDTO();
    @Override
    public void setAllFieldsFromEntity(StudentCourseAssignmentEntity entity) {
        this.id= entity.getId();
        this.marks=entity.getMarks();
        this.studentDTO.setAllFieldsFromEntity(entity.getStudentEntity());
    }

}
