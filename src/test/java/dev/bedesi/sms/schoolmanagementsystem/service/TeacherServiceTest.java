package dev.bedesi.sms.schoolmanagementsystem.service;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.TeacherEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TeacherServiceTest {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherService teacherService;

    private TeacherEntity testTeacherEntity;

    private void deleteRecordFromRepository(int id){
        teacherRepository.deleteById(id);
    }

    @BeforeEach
    void setUp() {
        testTeacherEntity = new TeacherEntity();
        testTeacherEntity.setId(1);
        testTeacherEntity.setName("John Doe");
    }
    @Test
    void testCreateTeacher_success(){
       teacherService.createTeacher(testTeacherEntity);
       Optional<TeacherEntity> resultTeacherEntity=teacherService.getTeacherById(1);

        assertTrue(resultTeacherEntity.isPresent(), "Teacher should be present in database");
        assertEquals(testTeacherEntity.getName(), resultTeacherEntity.get().getName(),
                "Teacher names should match");
        assertEquals(testTeacherEntity.getId(), resultTeacherEntity.get().getId(),
                "Teacher IDs should match");
    }


}
