package com.lms.backend.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.course.entity.Course;
import com.lms.backend.course.entity.CourseStatus;
import com.lms.backend.user.entity.User;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructor(User instructor);

    List<Course> findByStatus(CourseStatus status);
}
