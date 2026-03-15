package com.project.student_attendance.entities.student_course;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.student_attendance.entities.Student;
import com.project.student_attendance.entities.attendance.Attendance;
import com.project.student_attendance.entities.course.Course;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_course")
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId id;

    @ManyToOne
    @MapsId("rollNo")
    @JoinColumn(name = "roll_no")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumns({
            @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
            @JoinColumn(name = "start_date", referencedColumnName = "start_date")
    })
    private Course course;

    @JsonManagedReference
    @OneToMany(mappedBy = "studentCourse")
    private List<Attendance> attendances;
}
