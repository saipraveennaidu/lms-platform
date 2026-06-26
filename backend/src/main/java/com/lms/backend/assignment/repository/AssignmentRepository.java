package com.lms.backend.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.assignment.entity.Assignment;
import com.lms.backend.course.entity.Course;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCourse(Course course);
}
