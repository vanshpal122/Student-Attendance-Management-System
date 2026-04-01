package com.project.student_attendance.service;

import com.project.student_attendance.dto.StudentDTO;
import com.project.student_attendance.entities.Student;
import com.project.student_attendance.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean doesStudentExist(String rollNo) {
        return studentRepository.existsById(rollNo);
    }

    public Optional<StudentDTO> getStudentDetails(String rollNo) {
        return studentRepository.findById(rollNo).map(Student::toStudentDTO);
    }
}
