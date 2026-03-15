package com.project.student_attendance.entities;

import com.project.student_attendance.entities.student_course.StudentCourse;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    private String rollNo;

    private String name;

    @ManyToOne
    @JoinColumn(name = "branch", referencedColumnName = "name")
    private Branch branch;

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourses;
}