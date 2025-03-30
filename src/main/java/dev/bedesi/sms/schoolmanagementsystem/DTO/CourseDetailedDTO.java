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
    private TeacherDTO teacherDTO = new TeacherDTO();

    private List<StudentCourseAssignmentDTO> StudentCourseAssignmentDTO = new ArrayList<>();

    @Override
    public void setAllFieldsFromEntity(CourseEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.active = entity.getActive();
        this.teacherDTO.setAllFieldsFromEntity(entity.getTeacher());
        this.StudentCourseAssignmentDTO = entity.getStudentCourseEntities().stream()
                .map(studentCourseAssignmentEntity -> {
                    StudentCourseAssignmentDTO studentCourseAssignmentDTO=new StudentCourseAssignmentDTO();
                    studentCourseAssignmentDTO.setAllFieldsFromEntity(studentCourseAssignmentEntity);
                    return  studentCourseAssignmentDTO;
                })
                .collect(Collectors.toList());
    }

}
