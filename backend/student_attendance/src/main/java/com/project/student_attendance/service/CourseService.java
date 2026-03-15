package com.project.student_attendance.service;

import com.project.student_attendance.entities.course.Course;
import com.project.student_attendance.repository.StudentCourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final StudentCourseRepository repository;

    public CourseService(StudentCourseRepository repository) {
        this.repository = repository;
    }

    public Page<Course> getCourses(String rollNo, Pageable pageable) {
        return repository.findCoursesByRollNo(rollNo, pageable);
    }
}
