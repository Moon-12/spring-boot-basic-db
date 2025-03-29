package dev.bedesi.sms.schoolmanagementsystem.controller;

import dev.bedesi.sms.schoolmanagementsystem.DTO.CourseDTO;
import dev.bedesi.sms.schoolmanagementsystem.DTO.StudentCourseAssignmentDTO;
import dev.bedesi.sms.schoolmanagementsystem.DTO.StudentDTO;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.CourseEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseAssignmentEntity;
import dev.bedesi.sms.schoolmanagementsystem.service.CourseService;
import dev.bedesi.sms.schoolmanagementsystem.service.StudentCourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/sms/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CourseDTO>> getCourseById(@PathVariable int id) {
        Optional<CourseEntity> courseEntityOptional = courseService.getCourseById(id);

        if (courseEntityOptional.isPresent()) {
            CourseEntity courseEntity = courseEntityOptional.get();
            int studentCount = courseEntity.getStudentCourseEntities().size();
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setAllFieldsFromEntity(courseEntity);
            return ResponseEntity.ok(Optional.of(courseDTO));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public CourseEntity createCourse(@RequestBody CourseEntity course) {
        return courseService.createCourse(course);
    }

    @PostMapping("/assign-teacher")
    public ResponseEntity<?> assignTeacher(@RequestBody CourseEntity course) {
        try {
            CourseDTO courseDTO = courseService.assignTeacher(course);
            return ResponseEntity.ok(courseDTO); // 200 OK with the updated course on success
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND) // 404 status
                    .body("course or teacher does not exist"); // Custom message
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict status
                    .body(e.getMessage()); // "Course with ID X already has a teacher assigned"
        }
    }

    @PostMapping("/assign-student")
    public ResponseEntity<?> assignStudent(@RequestBody StudentCourseAssignmentEntity studentCourse) {
        try {
            StudentCourseAssignmentDTO studentCourseAssignmentDTO = courseService.assignStudent(studentCourse);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Student successfully assigned to course");
            response.put("data", studentCourseAssignmentDTO);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response); // 200 OK with the updated course on success
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND) // 404 status
                    .body("student or course does not exist"); // Custom message
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409 Conflict status
                    .body(e.getMessage()); // ""Student with ID is already actively enrolled in course with ID"
        }
    }
}