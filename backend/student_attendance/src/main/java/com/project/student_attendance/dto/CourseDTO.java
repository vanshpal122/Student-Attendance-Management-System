package com.project.student_attendance.dto;

import java.time.LocalDate;

public record CourseDTO(
        String courseCode,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String branch,
        String instructorId
) {
}
