package dev.bedesi.sms.schoolmanagementsystem.DTO;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.TeacherEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherDTO implements TranslateDTO<TeacherEntity>{
    private String name;

    @Override
    public void setAllFieldsFromEntity(TeacherEntity entity) {
        this.name= entity.getName();
    }
}
