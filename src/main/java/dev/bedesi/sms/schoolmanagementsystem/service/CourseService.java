package dev.bedesi.sms.schoolmanagementsystem.service;

import dev.bedesi.sms.schoolmanagementsystem.DTO.CourseDTO;
import dev.bedesi.sms.schoolmanagementsystem.DTO.CourseDetailedDTO;
import dev.bedesi.sms.schoolmanagementsystem.DTO.StudentCourseAssignmentDTO;
import dev.bedesi.sms.schoolmanagementsystem.DTO.StudentDTO;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.CourseEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseAssignmentEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.TeacherEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.CourseRepository;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentCourseService studentCourseService;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseEntity -> {
                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setAllFieldsFromEntity(courseEntity);
                    return courseDTO;
                })
                .collect(Collectors.toList());
    }

    public Optional<CourseEntity> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public CourseEntity createCourse(CourseEntity course) {
        return courseRepository.save(course);
    }

    public CourseDetailedDTO assignTeacher(CourseEntity courseEntity) {
        Objects.requireNonNull(courseEntity, "Course cannot be null");
        CourseEntity existingCourse = courseRepository.findById(courseEntity.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Course with ID " + courseEntity.getId() + " not found or already inactive"));

        // Check if course already has a teacher
        if (existingCourse.getTeacher() != null) {
            throw new IllegalStateException("Course with ID " + courseEntity.getId() + " already has a teacher assigned");
        }

        TeacherEntity teacher = teacherRepository.findById(courseEntity.getTeacher().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Teacher with ID " + courseEntity.getTeacher().getId() + " not found or already inactive"));

        existingCourse.setTeacher(teacher);
        CourseEntity savedCourseEntity =courseRepository.save(existingCourse);
        CourseDetailedDTO courseDTO= new CourseDetailedDTO();
        courseDTO.setAllFieldsFromEntity(savedCourseEntity);
        return courseDTO;
    }

    public StudentCourseAssignmentDTO assignStudent(StudentCourseAssignmentEntity studentCourseAssignmentEntity) {
        int courseID = studentCourseAssignmentEntity.getCourseEntity().getId();
        int stdID = studentCourseAssignmentEntity.getStudentEntity().getId();
         courseRepository.findById(courseID)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Course with ID " + courseID + " not found or already inactive"));
        studentService.getStudentById(stdID)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Teacher with ID " + stdID + " not found or already inactive"));

        // Check if an active enrollment exists
        if (studentCourseService.checkEnrollmentActive(studentCourseAssignmentEntity)) {
            throw new IllegalStateException(
                    "Student with ID " + stdID + " is already actively enrolled in course with ID " + courseID);
        }

        StudentCourseAssignmentEntity savedStudentCourseAssignmentEntity =studentCourseService.enrollStudent(studentCourseAssignmentEntity);
        StudentCourseAssignmentDTO studentCourseAssignmentDTO=new StudentCourseAssignmentDTO();
        studentCourseAssignmentDTO.setAllFieldsFromEntity(savedStudentCourseAssignmentEntity);
        return studentCourseAssignmentDTO ;
    }
}