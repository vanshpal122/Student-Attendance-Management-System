package com.project.student_attendance.repository;

import com.project.student_attendance.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,String> {
    boolean existsById(String id);
}
