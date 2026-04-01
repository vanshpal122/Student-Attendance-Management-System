package com.project.student_attendance.repository;

import com.project.student_attendance.entities.course.Course;
import com.project.student_attendance.entities.course.CourseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, CourseId> {
    List<Course> findByInstructorId(String instructorId);
}
