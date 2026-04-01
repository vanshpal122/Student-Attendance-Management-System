package com.project.student_attendance.service;

import com.project.student_attendance.dto.InstructorDTO;
import com.project.student_attendance.entities.Instructor;
import com.project.student_attendance.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorService {
    InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public boolean doesInstructorExist(String instructorId) {
        return instructorRepository.existsById(instructorId);
    }

    public Optional<InstructorDTO> getInstructorDetails(String instructorId) {
        return instructorRepository.findById(instructorId).map(Instructor::toDTO);
    }
}