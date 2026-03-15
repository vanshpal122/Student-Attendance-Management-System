package com.project.student_attendance.repository;

import com.project.student_attendance.entities.course.Course;
import com.project.student_attendance.entities.course.CourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, CourseId> {

}
