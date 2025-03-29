package dev.bedesi.sms.schoolmanagementsystem.service;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCourseService {
    @Autowired
    StudentCourseRepository studentCourseRepository;

    public StudentCourseEntity enrollStudent(StudentCourseEntity studentCourseEntity) {
        return studentCourseRepository.save(studentCourseEntity);
    }
    public boolean checkEnrollmentActive(StudentCourseEntity studentCourseEntity) {
        Optional<StudentCourseEntity> stdCourseOpt= studentCourseRepository.findByStudentIdAndCourseId(studentCourseEntity.getStudentId(),studentCourseEntity.getCourseId());
        return stdCourseOpt.isPresent();
    }
}
