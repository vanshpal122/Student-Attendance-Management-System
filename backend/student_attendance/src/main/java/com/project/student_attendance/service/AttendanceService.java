package com.project.student_attendance.service;

import com.project.student_attendance.dto.*;
import com.project.student_attendance.entities.attendance.Attendance;
import com.project.student_attendance.entities.attendance.AttendanceId;
import com.project.student_attendance.entities.course.CourseId;
import com.project.student_attendance.entities.day.WorkingDay;
import com.project.student_attendance.entities.student_course.StudentCourse;
import com.project.student_attendance.entities.student_course.StudentCourseId;
import com.project.student_attendance.repository.AttendanceRepository;
import com.project.student_attendance.repository.OffDayRepository;
import com.project.student_attendance.repository.StudentCourseRepository;
import com.project.student_attendance.repository.WorkingDayRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final OffDayRepository offDayRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final WorkingDayService workingDayService;

    public AttendanceService(AttendanceRepository attendanceRepository, OffDayRepository offDayRepository, StudentCourseRepository studentCourseRepository, WorkingDayService workingDayService) {
        this.attendanceRepository = attendanceRepository;
        this.offDayRepository = offDayRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.workingDayService = workingDayService;
    }

    // Single date calendar
    public DayStatusDTO getDayStatus(
            String courseCode,
            LocalDate startDate,
            LocalDate date
    ) {
        // Fetch only for this specific date
        OffDayDTO offDay = offDayRepository.findCourseOffDays(
                courseCode, startDate, date, date
        ).stream().findFirst().orElse(null);



        if (offDay != null) {
            return new DayStatusDTO(
                    date,
                    false,
                    true,
                    offDay.getReasonOfDayOff()
            );
        } else if (workingDayService.isWorkingDay(date)) {
            return new DayStatusDTO(
                    date,
                    true,
                    true,
                    null
            );
        } else {
            return new DayStatusDTO(
                    date,
                    null,
                    false,
                    null
            );
        }
    }

    public List<StudentDayStatusDTO> getMonthlyCalendar(
            String rollNo,
            String courseCode,
            LocalDate startDate,
            int year,
            int month
    ) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        List<AttendanceDTO> attendance =
                attendanceRepository.findMonthlyAttendance(
                        rollNo, courseCode, startDate, start, end
                );

        List<OffDayDTO> offDays =
                offDayRepository.findCourseOffDays(
                        courseCode, startDate, start, end
                );

        Map<LocalDate, AttendanceDTO> attendanceMap =
                attendance.stream()
                        .collect(Collectors.toMap(AttendanceDTO::date, a -> a));

        Map<LocalDate, OffDayDTO> offDayMap =
                offDays.stream()
                        .collect(Collectors.toMap(OffDayDTO::getDate, o -> o));

        // Stream over each day of the month
        return start.datesUntil(end.plusDays(1))
                .map(current -> {
                    AttendanceDTO a = attendanceMap.get(current);
                    OffDayDTO o = offDayMap.get(current);

                    DayStatusDTO dayStatus;
                    Boolean isPresent = null;
                    String reasonOfAbsence = null;

                    if (o != null) {
                        dayStatus = new DayStatusDTO(
                                current,
                                false,                 // isWorkingDay
                                true,                  // isMarked
                                o.getReasonOfDayOff()  // offDayReason
                        );
                    } else if (a != null) {
                        dayStatus = new DayStatusDTO(
                                current,
                                true,   // isWorkingDay
                                true,   // isMarked
                                null    // offDayReason
                        );
                        isPresent = a.isPresent();
                        reasonOfAbsence = a.reasonOfAbsence();
                    } else {
                        dayStatus = new DayStatusDTO(
                                current,
                                null,   // isWorkingDay unknown
                                false,  // not marked
                                null    // offDayReason
                        );
                    }

                    return new StudentDayStatusDTO(dayStatus, isPresent, reasonOfAbsence);
                })
                .toList();
    }

    public void markAttendanceBatch(
            List<MarkAttendanceRequest> request
    ) {
        request.forEach(this::markAttendance);
    }

    @Transactional
    public void markAttendance(
            MarkAttendanceRequest markAttendanceDTO
    ) {
        String rollNo = markAttendanceDTO.getRollNo();
        String courseCode = markAttendanceDTO.getCourseCode();
        LocalDate courseStartDate = markAttendanceDTO.getCourseStartDate();
        LocalDate date = markAttendanceDTO.getDate();
        Boolean isPresent = markAttendanceDTO.getIsPresent();
        String reason = markAttendanceDTO.getReasonOfAbsence();
        CourseId courseId = new CourseId(courseCode, courseStartDate);
        StudentCourseId studentCourseId =
                new StudentCourseId(rollNo, courseId);

        StudentCourse studentCourse = studentCourseRepository
                .findById(studentCourseId)
                .orElseThrow(() -> new RuntimeException("StudentCourse not found"));

        // Ensure WorkingDay exists
        WorkingDay workingDay = workingDayService.getAndSave(date);

        AttendanceId attendanceId = new AttendanceId(studentCourseId, date);

        Attendance attendance = new Attendance();
        attendance.setId(attendanceId);
        attendance.setStudentCourse(studentCourse);
        attendance.setWorkingDay(workingDay);
        attendance.setPresent(isPresent);
        attendance.setReasonOfAbsence(reason);

        workingDay.getAttendances().add(attendance);
        attendanceRepository.save(attendance);
    }

}
