package com.project.student_attendance.service;

import com.project.student_attendance.entities.course.Course;
import com.project.student_attendance.entities.course.CourseId;
import com.project.student_attendance.entities.day.OffDay;
import com.project.student_attendance.repository.CourseRepository;
import com.project.student_attendance.repository.OffDayRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OffDayService {

    private final OffDayRepository offDayRepository;
    private final CourseRepository courseRepository;

    public OffDayService(OffDayRepository offDayRepository, CourseRepository courseRepository) {
        this.offDayRepository = offDayRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void markDayAsOff(String courseCode, LocalDate startDate, LocalDate date, String reasonOfDayOff) {

        CourseId courseId = new CourseId(courseCode, startDate);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        OffDay offDay = offDayRepository.findById(date)
                .orElseGet(() -> {
                    OffDay o = new OffDay();
                    o.setDate(date);
                    o.setReasonOfDayOff(reasonOfDayOff);
                    return offDayRepository.save(o);
                });

        course.getOffDays().add(offDay);

        courseRepository.save(course);
    }
}
