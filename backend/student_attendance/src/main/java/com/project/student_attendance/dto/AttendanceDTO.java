package com.project.student_attendance.dto;

import java.time.LocalDate;


public record AttendanceDTO(
        LocalDate date,
        boolean isPresent,
        String reasonOfAbsence
) {
}