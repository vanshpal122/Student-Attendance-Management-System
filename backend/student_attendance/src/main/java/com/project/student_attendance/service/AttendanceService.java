package com.project.student_attendance.service;

import com.project.student_attendance.dto.AttendanceCalendarDTO;
import com.project.student_attendance.dto.AttendanceDTO;
import com.project.student_attendance.dto.OffDayDTO;
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
    private final WorkingDayRepository workingDayRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, OffDayRepository offDayRepository, StudentCourseRepository studentCourseRepository, WorkingDayRepository workingDayRepository) {
        this.attendanceRepository = attendanceRepository;
        this.offDayRepository = offDayRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.workingDayRepository = workingDayRepository;
    }

    public List<AttendanceCalendarDTO> getMonthlyCalendar(
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
                        .collect(Collectors.toMap(
                                AttendanceDTO::date,
                                a -> a
                        ));

        Map<LocalDate, OffDayDTO> offDayMap =
                offDays.stream()
                        .collect(Collectors.toMap(
                                OffDayDTO::getDate,
                                o -> o
                        ));

        List<AttendanceCalendarDTO> calendar = new ArrayList<>();

        LocalDate current = start;

        while (!current.isAfter(end)) {

            AttendanceDTO a = attendanceMap.get(current);
            OffDayDTO o = offDayMap.get(current);

            if (o != null) {
                calendar.add(new AttendanceCalendarDTO(
                        current,
                        false,
                        null,
                        null,
                        o.getReasonOfDayOff()
                ));
            } else if (a != null) {
                calendar.add(new AttendanceCalendarDTO(
                        current,
                        true,
                        a.isPresent(),
                        a.reasonOfAbsence(),
                        null
                ));
            } else {
                calendar.add(new AttendanceCalendarDTO(
                        current,
                        true,
                        null,
                        null,
                        null
                ));
            }

            current = current.plusDays(1);
        }

        return calendar;
    }

    @Transactional
    public void markAttendance(
            String rollNo,
            String courseCode,
            LocalDate courseStartDate,
            LocalDate date,
            Boolean isPresent,
            String reason
    ) {
        CourseId courseId = new CourseId(courseCode, courseStartDate);
        StudentCourseId studentCourseId =
                new StudentCourseId(rollNo, courseId);

        StudentCourse studentCourse = studentCourseRepository
                .findById(studentCourseId)
                .orElseThrow(() -> new RuntimeException("StudentCourse not found"));

        // Ensure WorkingDay exists
        WorkingDay workingDay = workingDayRepository
                .findById(date)
                .orElseGet(() -> {
                    WorkingDay wd = new WorkingDay();
                    wd.setDate(date);
                    return workingDayRepository.save(wd);
                });

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
