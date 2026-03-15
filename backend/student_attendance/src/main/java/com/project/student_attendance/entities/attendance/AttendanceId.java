package com.project.student_attendance.entities.attendance;

import com.project.student_attendance.entities.student_course.StudentCourseId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor //Important for JPA
@AllArgsConstructor
@Embeddable
public class AttendanceId implements Serializable {
    @Embedded
    private StudentCourseId studentCourseId;

    @Column(name = "date")
    private LocalDate date;       // attendance date

    // equals() and hashCode() — critical for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceId)) return false;
        AttendanceId that = (AttendanceId) o;
        return Objects.equals(studentCourseId, that.studentCourseId) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentCourseId, date);
    }

    // Optional: toString() for debugging
    @Override
    public String toString() {
        return "AttendanceId{" +
                "rollNo=" + studentCourseId.getRollNo() +
                ", courseCode=" + studentCourseId.getCourseId().getCourseCode() +
                ", startDate=" + studentCourseId.getCourseId().getStartDate() +
                ", date=" + date +
                '}';
    }
}