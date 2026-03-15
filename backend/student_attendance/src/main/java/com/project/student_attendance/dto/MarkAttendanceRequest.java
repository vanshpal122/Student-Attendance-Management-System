package com.project.student_attendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MarkAttendanceRequest {

    private String rollNo;
    private String courseCode;
    private LocalDate courseStartDate;
    private LocalDate date;
    private Boolean isPresent;
    private String reasonOfAbsence;
}