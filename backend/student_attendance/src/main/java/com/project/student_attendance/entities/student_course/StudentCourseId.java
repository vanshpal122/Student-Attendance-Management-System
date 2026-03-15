package com.project.student_attendance.entities.student_course;

import com.project.student_attendance.entities.course.CourseId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class StudentCourseId implements Serializable {

    @Column(name = "roll_no")
    private String rollNo;

    @Embedded
    private CourseId courseId; // embed the composite key here

    // getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCourseId)) return false;
        StudentCourseId other = (StudentCourseId) o;
        return Objects.equals(rollNo, other.rollNo)
                && Objects.equals(courseId, other.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo, courseId);
    }
}