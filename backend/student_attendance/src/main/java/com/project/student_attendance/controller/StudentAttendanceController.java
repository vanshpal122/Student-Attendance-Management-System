package com.project.student_attendance.controller;

import com.project.student_attendance.dto.StudentDayStatusDTO;
import com.project.student_attendance.dto.CourseDTO;
import com.project.student_attendance.dto.StudentDTO;
import com.project.student_attendance.service.AttendanceService;
import com.project.student_attendance.service.StudentCourseService;
import com.project.student_attendance.service.StudentService;
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

    private final StudentCourseService studentCourseService;
    private final AttendanceService attendanceService;
    private final StudentService studentService;

    public StudentAttendanceController(StudentCourseService studentCourseService, AttendanceService attendanceService, StudentService studentService) {
        this.studentCourseService = studentCourseService;
        this.attendanceService = attendanceService;
        this.studentService = studentService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public StudentDTO getStudent(
            @RequestParam String rollNo
    ) {
        return studentService.getStudentDetails(rollNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found"));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{rollNo}/courses")
    public Page<CourseDTO> getCourses(
            @PathVariable String rollNo,
            @PageableDefault(size = 10)
            Pageable pageable
    ) {
        if (!studentService.doesStudentExist(rollNo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id" + rollNo + "not found");
        }
        return studentCourseService.getCourses(rollNo, pageable);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{rollNo}/courses/{courseCode}/{startDate}/calendar")
    public List<StudentDayStatusDTO> getMonthlyCalendar(
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
