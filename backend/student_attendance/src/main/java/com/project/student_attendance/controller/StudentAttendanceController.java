package com.project.student_attendance.controller;

import com.project.student_attendance.dto.AttendanceCalendarDTO;
import com.project.student_attendance.dto.MarkAttendanceRequest;
import com.project.student_attendance.dto.OffDayRequest;
import com.project.student_attendance.entities.course.Course;
import com.project.student_attendance.service.AttendanceService;
import com.project.student_attendance.service.CourseService;
import com.project.student_attendance.service.OffDayService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentAttendanceController {

    private final CourseService courseService;
    private final AttendanceService attendanceService;
    private final OffDayService offDayService;

    public StudentAttendanceController(CourseService courseService, AttendanceService attendanceService, OffDayService offDayService) {
        this.courseService = courseService;
        this.attendanceService = attendanceService;
        this.offDayService = offDayService;
    }

    @GetMapping("/{rollNo}/courses")
    public Page<Course> getCourses(
            @PathVariable String rollNo,
            @PageableDefault(size = 10)
            Pageable pageable
    ) {
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
