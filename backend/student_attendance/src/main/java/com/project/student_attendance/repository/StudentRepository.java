package com.project.student_attendance.repository;

import com.project.student_attendance.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsById(String rollNo);
}