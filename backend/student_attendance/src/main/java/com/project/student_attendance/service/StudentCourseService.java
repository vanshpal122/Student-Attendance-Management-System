package com.project.student_attendance.service;

import com.project.student_attendance.dto.CourseDTO;
import com.project.student_attendance.dto.StudentDTO;
import com.project.student_attendance.entities.Student;
import com.project.student_attendance.repository.StudentCourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentCourseService {
    private StudentCourseRepository studentCourseRepository;

    public StudentCourseService(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    public Page<CourseDTO> getCourses(String rollNo, Pageable pageable) {
        return studentCourseRepository.findCoursesByRollNo(rollNo, pageable).map(course -> course.mapToDTO(course));
    }

    public List<StudentDTO> getEnrolledStudents(String courseCode, LocalDate startDate) {
        return studentCourseRepository.getEnrolledStudents(courseCode, startDate).stream().map(stC -> Student.toStudentDTO(stC.getStudent())).toList();
    }
}
