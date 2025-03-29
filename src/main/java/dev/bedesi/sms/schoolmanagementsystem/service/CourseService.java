package dev.bedesi.sms.schoolmanagementsystem.service;

import dev.bedesi.sms.schoolmanagementsystem.DTO.CourseDTO;
import dev.bedesi.sms.schoolmanagementsystem.DTO.StudentDTO;
import dev.bedesi.sms.schoolmanagementsystem.DTO.TeacherDTO;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.CourseEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentCourseEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.StudentEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.entity.TeacherEntity;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.CourseRepository;
import dev.bedesi.sms.schoolmanagementsystem.mysql.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        List<CourseDTO> courseDaoList = new ArrayList<>();
        courseRepository.findAll().forEach(courseEntity ->
                courseDaoList.add(new CourseDTO(courseEntity.getId(), courseEntity.getName(),courseEntity.getActive())));
        return courseDaoList;
    }

    public Optional<CourseEntity> getCourseById(int id) {
        return courseRepository.findById(id)
                .map(course -> {
                    int teacherId = course.getTeacher() != null ? course.getTeacher().getId() : 0;
                    if (teacherId != 0) {
                        return teacherService.getTeacherById(teacherId)
                                .map(teacher -> {
                                    course.setTeacher(teacher);
                                    return course;
                                })
                                .orElse(course);
                    }
                    return course;
                });
    }

    public CourseEntity createCourse(CourseEntity course) {
        return courseRepository.save(course);
    }

    public TeacherDTO assignTeacher(CourseEntity courseEntity) {
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
        return new TeacherDTO(savedCourseEntity.getTeacher().getId());
    }

    public StudentDTO assignStudent(StudentCourseEntity studentCourseEntity) {
        int courseID = studentCourseEntity.getCourseId();
        int stdID = studentCourseEntity.getStudentId();
        CourseEntity existingCourse = courseRepository.findById(courseID)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Course with ID " + courseID + " not found or already inactive"));
        StudentEntity existingStudent = studentService.getStudentById(stdID)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Teacher with ID " + stdID + " not found or already inactive"));

        // Check if an active enrollment exists
        if (studentCourseService.checkEnrollmentActive(studentCourseEntity)) {
            throw new IllegalStateException(
                    "Student with ID " + stdID + " is already actively enrolled in course with ID " + courseID);
        }

        StudentCourseEntity savedStudentCourseEntity=studentCourseService.enrollStudent(studentCourseEntity);
        return new StudentDTO(existingStudent.getId(),existingStudent.getRollNo(),existingStudent.getName());
    }
}