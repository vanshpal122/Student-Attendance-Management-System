package com.project.student_attendance.controller;

import com.project.student_attendance.dto.MarkAttendanceRequest;
import com.project.student_attendance.dto.OffDayRequest;
import com.project.student_attendance.service.AttendanceService;
import com.project.student_attendance.service.OffDayService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final OffDayService offDayService;
    private final AttendanceService attendanceService;

    public TeacherController(AttendanceService attendanceService, OffDayService offDayService) {
        this.attendanceService = attendanceService;
        this.offDayService = offDayService;
    }

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
