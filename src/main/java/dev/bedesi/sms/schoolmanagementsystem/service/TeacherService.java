package dev.bedesi.sms.schoolmanagementsystem.service;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.TeacherEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherEntity> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<TeacherEntity> getTeacherById(int id) {
        return teacherRepository.findById(id);
    }

    public TeacherEntity createTeacher(TeacherEntity teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(int id) {
        // Check if student exists with active = true
        Optional<TeacherEntity> teacherOpt = teacherRepository.findById(id);

        if (teacherOpt.isPresent()) {
            // Get the student and set active = false
            TeacherEntity teacher = teacherOpt.get();
            teacher.setActive(false);
            teacherRepository.save(teacher); // Persist the change
        } else {
            throw new EntityNotFoundException("Student with ID " + id + " not found or already inactive");
        }
    }
}