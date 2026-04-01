package com.project.student_attendance.controller;

import com.project.student_attendance.dto.*;
import com.project.student_attendance.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final OffDayService offDayService;
    private final AttendanceService attendanceService;
    private final StudentCourseService studentCourseService;
    private final CourseService courseService;
    private final InstructorService instructorService;

    public TeacherController(AttendanceService attendanceService, OffDayService offDayService, StudentCourseService studentCourseService, CourseService courseService, InstructorService instructorService) {
        this.attendanceService = attendanceService;
        this.offDayService = offDayService;
        this.studentCourseService = studentCourseService;
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    public InstructorDTO getInstructor(
            @RequestParam String id
    ) {
        return instructorService.getInstructorDetails(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor Not Found"));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/offday")
    @ResponseStatus(HttpStatus.CREATED)
    public void markDayAsOff(
            @RequestBody OffDayRequest request
    ) {
        System.out.println(request.toString());
        offDayService.markDayAsOff(
                request.getCourseCode(),
                request.getStartDate(),
                request.getDate(),
                request.getReasonOfDayOff()
        );
    }

    @CrossOrigin("*")
    @GetMapping("/{instructorId}/courses")
    public List<CourseDTO> getCourses(@PathVariable String instructorId) {
        if (!instructorService.doesInstructorExist(instructorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor Not Found");
        }
        return courseService.getCoursesFromInstructorId(instructorId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{courseCode}/{startDate}/enrolledStudents")
    public List<StudentDTO> getEnrolledStudents(
            @PathVariable String courseCode,
            @PathVariable LocalDate startDate
    ) {
        return studentCourseService.getEnrolledStudents(courseCode, startDate);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/markattendance")
    @ResponseStatus(HttpStatus.CREATED)
    public void markAttendance(@RequestBody MarkAttendanceRequest request) {
        attendanceService.markAttendance(request);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/markattendance/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public void markAttendanceBatch(@RequestBody List<MarkAttendanceRequest> request) {
        attendanceService.markAttendanceBatch(request);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{courseCode}/{startDate}/{date}/status")
    public DayStatusDTO getDayStatus(
            @PathVariable String courseCode,
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate date
    ) {
        return attendanceService.getDayStatus(
                courseCode, startDate, date
        );
    }
}
