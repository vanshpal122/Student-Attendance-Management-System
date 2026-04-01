package com.project.student_attendance.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.student_attendance.dto.StudentDTO;
import com.project.student_attendance.entities.student_course.StudentCourse;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "student")
@Getter
public class Student {

    @Id
    private String rollNo;

    private String name;

    @ManyToOne
    @JoinColumn(name = "branch", referencedColumnName = "name")
    private Branch branch;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private List<StudentCourse> studentCourses;

    public static StudentDTO toStudentDTO(Student student) {
        return new StudentDTO(
                student.getRollNo(),
                student.getName(),
                student.getBranch().getName()
        );
    }
}