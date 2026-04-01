package com.project.student_attendance.service;

import com.project.student_attendance.entities.day.WorkingDay;
import com.project.student_attendance.repository.WorkingDayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkingDayService {
    private WorkingDayRepository workingDayRepository;

    public WorkingDayService(WorkingDayRepository workingDayRepository) {
        this.workingDayRepository = workingDayRepository;
    }

    public WorkingDay getAndSave(LocalDate date) {
        return workingDayRepository
                .findById(date)
                .orElseGet(() -> {
                    WorkingDay wd = new WorkingDay();
                    wd.setDate(date);
                    return workingDayRepository.save(wd);
                });
    }

    public boolean isWorkingDay(LocalDate date) {
        return workingDayRepository.existsById(date);
    }
}
