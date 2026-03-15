package com.project.student_attendance.entities.course;

import com.project.student_attendance.entities.Branch;
import com.project.student_attendance.entities.day.OffDay;
import com.project.student_attendance.entities.student_course.StudentCourse;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "course")
public class Course {

    @EmbeddedId
    private CourseId id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "branch", referencedColumnName = "name")
    private Branch branch;

    @OneToMany(mappedBy = "course")
    private List<StudentCourse> studentCourses;

    @ManyToMany
    @JoinTable(
            name = "course_off_day",
            joinColumns = {
                    @JoinColumn(name = "course_code", referencedColumnName = "course_code"),
                    @JoinColumn(name = "start_date", referencedColumnName = "start_date")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "off_day_date", referencedColumnName = "date")
            }
    )
    private Set<OffDay> offDays = new HashSet<>();
}
