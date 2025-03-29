package dev.bedesi.sms.schoolmanagementsystem.mysql.repository;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.TeacherEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TeacherRepositoryTest {


    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void findAll_ReturnsAllTeachers() {
        // Given
        TeacherEntity teacher1 = new TeacherEntity("John Doe");
        TeacherEntity teacher2 = new TeacherEntity("Jane Smith");
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);

        // When
        List<TeacherEntity> teachers = teacherRepository.findAll();

        // Then
        assertThat(teachers).hasSize(2);
        assertThat(teachers).extracting(TeacherEntity::getName)
                .containsExactlyInAnyOrder("John Doe", "Jane Smith");
    }

    @Test
    void findById_ExistingId_ReturnsTeacher() {
        // Given
        TeacherEntity teacher = new TeacherEntity("John Doe");
        TeacherEntity savedTeacher = teacherRepository.save(teacher);

        // When
        Optional<TeacherEntity> foundTeacher = teacherRepository.findById(savedTeacher.getId());

        // Then
        assertThat(foundTeacher).isPresent();
        assertThat(foundTeacher.get().getName()).isEqualTo("John Doe");
    }

    @Test
    void findById_NonExistingId_ReturnsEmpty() {
        // When
        Optional<TeacherEntity> foundTeacher = teacherRepository.findById(999);

        // Then
        assertThat(foundTeacher).isNotPresent();
    }

    @Test
    void save_NewTeacher_ReturnsSavedTeacherWithId() {
        // Given
        TeacherEntity teacher = new TeacherEntity("John Doe");

        // When
        TeacherEntity savedTeacher = teacherRepository.save(teacher);

        // Then
        assertThat(savedTeacher.getId()).isNotNull();
        assertThat(savedTeacher.getName()).isEqualTo("John Doe");
    }

    @Test
    @Sql(statements = "INSERT INTO teacher (id, name) VALUES (1, 'Test Teacher')")
    void findAll_WithPreloadedData_ReturnsCorrectData() {
        // When
        List<TeacherEntity> teachers = teacherRepository.findAll();

        // Then
        assertThat(teachers).hasSize(1);
        assertThat(teachers.get(0).getId()).isEqualTo(1L);
        assertThat(teachers.get(0).getName()).isEqualTo("Test Teacher");
    }
}