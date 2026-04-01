package com.project.student_attendance.entities;

import com.project.student_attendance.dto.InstructorDTO;
import com.project.student_attendance.entities.course.Course;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "instructor")
@Getter
public class Instructor {
    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "branch", referencedColumnName = "name")
    private Branch branch;

    @OneToMany(mappedBy = "instructor")
    private List<Course> teaches;

    public static InstructorDTO toDTO(Instructor instructor) {
        return new InstructorDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getBranch().getName()
        );
    }
}
