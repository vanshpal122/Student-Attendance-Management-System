package com.project.student_attendance.entities.student_course;

import com.project.student_attendance.entities.Student;
import com.project.student_attendance.entities.attendance.Attendance;
import com.project.student_attendance.entities.course.Course;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "student_course")
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rollNo")
    @JoinColumn(name = "roll_no")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumns({
            @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
            @JoinColumn(name = "start_date", referencedColumnName = "start_date")
    })
    private Course course;
    @OneToMany(mappedBy = "studentCourse")
    private List<Attendance> attendances;
}
