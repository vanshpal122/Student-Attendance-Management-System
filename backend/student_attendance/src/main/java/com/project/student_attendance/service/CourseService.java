package com.project.student_attendance.service;

import com.project.student_attendance.dto.CourseDTO;
import com.project.student_attendance.entities.course.Course;
import com.project.student_attendance.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<CourseDTO> getCoursesFromInstructorId(String instructorId) {
        return repository.findByInstructorId(instructorId)
                .stream()
                .map(Course::mapToDTO)
                .toList();
    }
}
