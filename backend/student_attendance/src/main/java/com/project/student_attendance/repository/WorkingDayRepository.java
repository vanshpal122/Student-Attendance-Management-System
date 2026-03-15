package com.project.student_attendance.repository;

import com.project.student_attendance.entities.day.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WorkingDayRepository extends JpaRepository<WorkingDay, LocalDate> {
}
