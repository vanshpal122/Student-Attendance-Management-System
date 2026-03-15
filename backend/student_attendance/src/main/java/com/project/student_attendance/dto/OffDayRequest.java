package com.project.student_attendance.dto;

import java.time.LocalDate;

public class OffDayRequest {

    private LocalDate date;
    private String reasonOfDayOff;

    public LocalDate getDate() {
        return date;
    }

    public String getReasonOfDayOff() {
        return reasonOfDayOff;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setReasonOfDayOff(String reasonOfDayOff) {
        this.reasonOfDayOff = reasonOfDayOff;
    }
}
