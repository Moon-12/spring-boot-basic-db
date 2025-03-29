package dev.bedesi.sms.schoolmanagementsystem.DTO;


import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO implements TranslateDTO<CourseEntity>{
    private int id;
    private String name;
    private boolean active;
    private TeacherDTO teacherDTO = new TeacherDTO();

    @Override
    public void setAllFieldsFromEntity(CourseEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.active = entity.getActive();
        this.teacherDTO.setAllFieldsFromEntity(entity.getTeacher());
    }
}
