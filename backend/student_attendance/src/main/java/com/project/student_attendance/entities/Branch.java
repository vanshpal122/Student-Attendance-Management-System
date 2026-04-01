package com.project.student_attendance.entities;

import com.project.student_attendance.entities.course.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "branch")
public class Branch {

    @Id
    private String name;  // assuming branch name is PK

    // optional bidirectional relation
    @OneToMany(mappedBy = "branch")
    private List<Student> students;

    @OneToMany(mappedBy = "branch")
    private List<Course> courses;

    @OneToMany(mappedBy = "branch")
    private List<Instructor> instructors;
}