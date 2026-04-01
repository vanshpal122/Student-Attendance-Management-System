package com.project.student_attendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OffDayRequest {

    private String courseCode;
    private LocalDate startDate;
    private LocalDate date;
    private String reasonOfDayOff;

    @Override
    public String toString() {
        return "CourseCode " + courseCode + "\n" +
                "startDate " + startDate + "\n" +
                "date " + date + "\n" +
                "reasonOfDayOff " + reasonOfDayOff;
    }
}
