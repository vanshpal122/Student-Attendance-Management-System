package com.project.student_attendance.repository;

import com.project.student_attendance.dto.AttendanceDTO;
import com.project.student_attendance.entities.attendance.Attendance;
import com.project.student_attendance.entities.attendance.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {

    @Query("""
            SELECT new com.project.student_attendance.dto.AttendanceDTO(
                a.id.date,
                a.isPresent,
                a.reasonOfAbsence
            )
            FROM Attendance a
            WHERE a.id.studentCourseId.rollNo = :rollNo
            AND a.id.studentCourseId.courseId.courseCode = :courseCode
            AND a.id.studentCourseId.courseId.startDate = :startDate
            AND a.id.date BETWEEN :start AND :end
            ORDER BY a.id.date
            """)
    List<AttendanceDTO> findMonthlyAttendance(
            String rollNo,
            String courseCode,
            LocalDate startDate,
            LocalDate start,
            LocalDate end
    );
}
