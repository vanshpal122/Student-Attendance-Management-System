package com.project.student_attendance.service;

import com.project.student_attendance.repository.StudentCourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentCourseService {
    private StudentCourseRepository studentCourseRepository;

    public  StudentCourseService(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    public List<String> getEnrolledStudents(String courseCode, LocalDate startDate) {
        return studentCourseRepository.getEnrolledStudents(courseCode, startDate);
    }
}
