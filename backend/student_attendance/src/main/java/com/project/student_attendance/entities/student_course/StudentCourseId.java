package com.project.student_attendance.entities.student_course;

import com.project.student_attendance.entities.course.CourseId;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class StudentCourseId implements Serializable {

    @Column(name = "roll_no")
    private String rollNo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "courseCode", column = @Column(name = "course_code")),
            @AttributeOverride(name = "startDate", column = @Column(name = "start_date"))
    })
    private CourseId courseId; // embed the composite key here
}