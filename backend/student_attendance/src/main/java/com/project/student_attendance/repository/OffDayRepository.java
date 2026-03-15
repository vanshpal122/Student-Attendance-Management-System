package com.project.student_attendance.repository;

import com.project.student_attendance.dto.OffDayDTO;
import com.project.student_attendance.entities.day.OffDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OffDayRepository extends JpaRepository<OffDay, LocalDate> {
    @Query("""
            SELECT new com.example.student_attendance.dto.OffDayDTO(
                od.date,
                od.reasonOfDayOff
            )
            FROM Course c
            JOIN c.offDays od
            WHERE c.id.courseCode = :courseCode
            AND c.id.startDate = :startDate
            AND od.date BETWEEN :start AND :end
            ORDER BY od.date
            """)
    List<OffDayDTO> findCourseOffDays(
            String courseCode,
            LocalDate startDate,
            LocalDate start,
            LocalDate end
    );
}
