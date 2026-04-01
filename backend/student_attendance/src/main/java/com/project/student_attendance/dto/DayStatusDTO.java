package com.project.student_attendance.dto;

import java.time.LocalDate;

public record DayStatusDTO(
        LocalDate date,
        Boolean isWorkingDay,
        Boolean isMarked,
        String offDayReason
) {}
