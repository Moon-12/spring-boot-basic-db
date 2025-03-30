package dev.bedesi.sms.schoolmanagementsystem.DTO;


import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailedDTO implements TranslateDTO<CourseEntity> {
    private int id;
    private String name;
    private boolean active;
    private TeacherDTO teacher = new TeacherDTO();

    private List<StudentDTO> studentList = new ArrayList<>();

    @Override
    public void setAllFieldsFromEntity(CourseEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.active = entity.getActive();
        this.teacher.setAllFieldsFromEntity(entity.getTeacher());
        this.studentList = entity.getStudentCourseEntities().stream()
                .map(studentCourseAssignmentEntity -> {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setAllFieldsFromEntity(studentCourseAssignmentEntity.getStudentEntity());
                    return studentDTO;
                })
                .collect(Collectors.toList());
    }

}
