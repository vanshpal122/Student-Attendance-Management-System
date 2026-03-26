package com.project.student_attendance.entities.attendance;

import com.project.student_attendance.entities.student_course.StudentCourseId;
import jakarta.persistence.*;
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
    @AttributeOverrides({
            @AttributeOverride(name = "rollNo", column = @Column(name = "roll_no")),
            @AttributeOverride(name = "courseId.courseCode", column = @Column(name = "course_code")),
            @AttributeOverride(name = "courseId.startDate", column = @Column(name = "start_date"))
    })
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
                "studentCourseId=" + studentCourseId +
                ", date=" + date +
                '}';
    }
}