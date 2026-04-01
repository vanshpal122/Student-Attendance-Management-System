package com.project.student_attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

public record StudentDayStatusDTO(
        DayStatusDTO dayStatusDTO,
        Boolean isPresent,
        String reasonOfAbsence
) {}
