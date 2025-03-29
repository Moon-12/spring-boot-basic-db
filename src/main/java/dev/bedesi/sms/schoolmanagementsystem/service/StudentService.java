package dev.bedesi.sms.schoolmanagementsystem.service;

import dev.bedesi.sms.schoolmanagementsystem.DTO.StudentDTO;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Optional<StudentDTO> getStudentById(int id) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        return studentEntity.map(entity -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setAllFieldsFromEntity(entity);
            return studentDTO;
        });
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentDTOList = new ArrayList<>();

        studentRepository.findAll().stream()
                .map(studentEntity -> {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setAllFieldsFromEntity(studentEntity);
                    return studentDTO;
                })
                .forEach(studentDTOList::add);
        return studentDTOList;

    }

    public StudentDTO createStudent(StudentEntity student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setAllFieldsFromEntity(studentRepository.save(student));
        return studentDTO;
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
