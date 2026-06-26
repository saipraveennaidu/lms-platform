package com.lms.backend.enrollment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.course.entity.Course;
import com.lms.backend.enrollment.entity.Enrollment;
import com.lms.backend.user.entity.User;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    
    Optional<Enrollment> findByStudentAndCourse(
            User student,
            Course course);

    List<Enrollment> findByStudent(User student);

}
