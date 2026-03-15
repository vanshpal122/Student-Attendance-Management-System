package com.project.student_attendance.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AttendanceCalendarDTO {

    private LocalDate date;
    private Boolean isWorkingDay;
    private Boolean isPresent;
    private String reasonOfAbsence;
    private String offDayReason;

    public AttendanceCalendarDTO(
            LocalDate date,
            Boolean isWorkingDay,
            Boolean isPresent,
            String reasonOfAbsence,
            String offDayReason
    ) {
        this.date = date;
        this.isWorkingDay = isWorkingDay;
        this.isPresent = isPresent;
        this.reasonOfAbsence = reasonOfAbsence;
        this.offDayReason = offDayReason;
    }
}
