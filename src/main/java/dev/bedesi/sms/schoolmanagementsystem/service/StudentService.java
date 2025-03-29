package dev.bedesi.sms.schoolmanagementsystem.service;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Optional<StudentEntity> getStudentById(int id) {
       return studentRepository.findById(id);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        // Check if student exists with active = true
        Optional<StudentEntity> studentOpt = studentRepository.findById(id);

        if (studentOpt.isPresent()) {
            // Get the student and set active = false
            StudentEntity student = studentOpt.get();
            student.setActive(false);
            studentRepository.save(student); // Persist the change
        } else {
            throw new EntityNotFoundException("Student with ID " + id + " not found or already inactive");
        }
    }

}
