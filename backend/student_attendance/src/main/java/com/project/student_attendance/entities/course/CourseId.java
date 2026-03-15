package com.project.student_attendance.entities.course;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class CourseId implements Serializable {

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "start_date")
    private LocalDate startDate;

    public CourseId(String courseCode, LocalDate startDate) {
        this.courseCode = courseCode;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseId)) return false;
        CourseId other = (CourseId) o;
        return Objects.equals(courseCode, other.courseCode)
                && Objects.equals(startDate, other.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, startDate);
    }
}
