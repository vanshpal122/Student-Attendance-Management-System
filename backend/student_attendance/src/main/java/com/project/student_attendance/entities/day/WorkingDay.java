package com.project.student_attendance.entities.day;

import com.project.student_attendance.entities.attendance.Attendance;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "working_day")
public class WorkingDay {

    @Id
    private LocalDate date;

    @OneToMany(mappedBy = "workingDay")
    private List<Attendance> attendances = new ArrayList<>();
}