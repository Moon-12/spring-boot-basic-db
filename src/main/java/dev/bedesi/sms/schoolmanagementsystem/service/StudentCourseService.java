package dev.bedesi.sms.schoolmanagementsystem.service;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseAssignmentEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentCourseService {
    @Autowired
    StudentCourseRepository studentCourseRepository;

    public StudentCourseAssignmentEntity enrollStudent(StudentCourseAssignmentEntity studentCourseAssignmentEntity) {
        return studentCourseRepository.save(studentCourseAssignmentEntity);
    }
    public boolean checkEnrollmentActive(StudentCourseAssignmentEntity studentCourseAssignmentEntity) {
        Optional<StudentCourseAssignmentEntity> stdCourseOpt= studentCourseRepository.findByStudentIdAndCourseId(studentCourseAssignmentEntity.getStudentEntity().getId(), studentCourseAssignmentEntity.getCourseEntity().getId());
        return stdCourseOpt.isPresent();
    }
}
