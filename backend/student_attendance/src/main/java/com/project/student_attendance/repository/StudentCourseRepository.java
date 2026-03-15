package com.project.student_attendance.repository;

import com.project.student_attendance.entities.course.Course;
import com.project.student_attendance.entities.student_course.StudentCourse;
import com.project.student_attendance.entities.student_course.StudentCourseId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentCourseRepository
        extends JpaRepository<StudentCourse, StudentCourseId> {

    @Query("""
            SELECT c
            FROM StudentCourse sc
            JOIN sc.course c
            WHERE sc.id.rollNo = :rollNo
            ORDER BY c.id.startDate DESC
            """)
    Page<Course> findCoursesByRollNo(
            @Param("rollNo") String rollNo,
            Pageable pageable
    );
}
