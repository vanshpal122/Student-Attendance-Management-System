package com.project.student_attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class OffDayDTO {

    private LocalDate date;
    private String reasonOfDayOff;

}