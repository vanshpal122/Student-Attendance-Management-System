package com.project.student_attendance.entities.attendance;

import com.project.student_attendance.entities.day.WorkingDay;
import com.project.student_attendance.entities.student_course.StudentCourse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "attendance")
public class Attendance {

    @EmbeddedId
    private AttendanceId id;

    @ManyToOne
    @MapsId("date")
    @JoinColumn(name = "date")
    private WorkingDay workingDay;

    @MapsId("studentCourseId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "roll_no", referencedColumnName = "roll_no"),
            @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
            @JoinColumn(name = "start_date", referencedColumnName = "start_date")
    })
    private StudentCourse studentCourse;

    private boolean isPresent;

    private String reasonOfAbsence;

    // convenience getter for attendance date
    public LocalDate getAttendanceDate() {
        return id.getDate();
    }
}
