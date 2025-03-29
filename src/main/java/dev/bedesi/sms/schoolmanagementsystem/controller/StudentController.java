package dev.bedesi.sms.schoolmanagementsystem.controller;

import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import dev.bedesi.sms.schoolmanagementsystem.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sms/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable int id){
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<StudentEntity> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentEntity createStudent(@RequestBody StudentEntity student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build(); // 204 No Content on success
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found if student doesn't exist or is inactive
        }
    }

}
