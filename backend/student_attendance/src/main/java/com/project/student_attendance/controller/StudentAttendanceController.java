package com.project.student_attendance.controller;

import com.project.student_attendance.dto.AttendanceCalendarDTO;
import com.project.student_attendance.entities.course.Course;
import com.project.student_attendance.repository.StudentRepository;
import com.project.student_attendance.service.AttendanceService;
import com.project.student_attendance.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentAttendanceController {

    private final CourseService courseService;
    private final AttendanceService attendanceService;
    private final StudentRepository studentRepository;

    public StudentAttendanceController(CourseService courseService, AttendanceService attendanceService, StudentRepository studentRepository) {
        this.courseService = courseService;
        this.attendanceService = attendanceService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{rollNo}/courses")
    public Page<Course> getCourses(
            @PathVariable String rollNo,
            @PageableDefault(size = 10)
            Pageable pageable
    ) {
        if (!studentRepository.existsById(rollNo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id" + rollNo + "not found");
        }
        return courseService.getCourses(rollNo, pageable);
    }

    @GetMapping("/{rollNo}/courses/{courseCode}/{startDate}/calendar")
    public List<AttendanceCalendarDTO> getMonthlyCalendar(
            @PathVariable String rollNo,
            @PathVariable String courseCode,
            @PathVariable LocalDate startDate,
            @RequestParam int year,
            @RequestParam int month
    ) {
        return attendanceService.getMonthlyCalendar(
                rollNo, courseCode, startDate, year, month
        );
    }
}
