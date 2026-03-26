package com.project.student_attendance.controller;

import com.project.student_attendance.dto.MarkAttendanceRequest;
import com.project.student_attendance.dto.OffDayRequest;
import com.project.student_attendance.service.AttendanceService;
import com.project.student_attendance.service.OffDayService;
import com.project.student_attendance.service.StudentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final OffDayService offDayService;
    private final AttendanceService attendanceService;
    private final StudentCourseService studentCourseService;

    public TeacherController(AttendanceService attendanceService, OffDayService offDayService, StudentCourseService studentCourseService) {
        this.attendanceService = attendanceService;
        this.offDayService = offDayService;
        this.studentCourseService = studentCourseService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{courseCode}/{startDate}/offdays")
    @ResponseStatus(HttpStatus.CREATED)
    public void markDayAsOff(
            @PathVariable String courseCode,
            @PathVariable LocalDate startDate,
            @RequestBody OffDayRequest request
    ) {
        offDayService.markDayAsOff(
                courseCode,
                startDate,
                request.getDate(),
                request.getReasonOfDayOff()
        );
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{courseCode}/{startDate}/enrolledStudents")
    public List<String> getEnrolledStudents(
            @PathVariable String courseCode,
            @PathVariable LocalDate startDate
    ) {
        return studentCourseService.getEnrolledStudents(courseCode, startDate);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/markattendance")
    @ResponseStatus(HttpStatus.CREATED)
    public void markAttendance(@RequestBody MarkAttendanceRequest request) {

        attendanceService.markAttendance(
                request.getRollNo(),
                request.getCourseCode(),
                request.getCourseStartDate(),
                request.getDate(),
                request.getIsPresent(),
                request.getReasonOfAbsence()
        );
    }
}
