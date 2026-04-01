package com.project.student_attendance.dto;

public record OverallAttendanceInfoDTO(
        int totalClasses,
        int presentCount,
        int offDays
) {
}
