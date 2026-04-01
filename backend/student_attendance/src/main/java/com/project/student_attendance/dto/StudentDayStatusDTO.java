package com.project.student_attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DayStatusDTO {

    private LocalDate date;
    private Boolean isWorkingDay;
    private Boolean isPresent;
    private Boolean isMarked;
    private String reasonOfAbsence;
    private String offDayReason;
}
