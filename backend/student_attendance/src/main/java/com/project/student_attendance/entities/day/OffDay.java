package com.project.student_attendance.entities.day;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "off_day")
@Setter
public class OffDay {

    @Id
    private LocalDate date;

    private String reasonOfDayOff;
}