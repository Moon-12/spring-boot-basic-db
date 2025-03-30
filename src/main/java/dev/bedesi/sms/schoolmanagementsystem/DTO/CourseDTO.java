package dev.bedesi.sms.schoolmanagementsystem.DTO;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO implements TranslateDTO<CourseEntity> {
    int id;
    String name;
    boolean active;

    @Override
    public void setAllFieldsFromEntity(CourseEntity entity) {
        this.id=entity.getId();
        this.name=entity.getName();
        this.active=entity.getActive();
    }
}
