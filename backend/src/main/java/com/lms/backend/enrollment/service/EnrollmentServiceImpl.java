package com.lms.backend.enrollment.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.course.entity.Course;
import com.lms.backend.course.repository.CourseRepository;
import com.lms.backend.enrollment.dto.EnrollmentResponse;
import com.lms.backend.enrollment.entity.Enrollment;
import com.lms.backend.enrollment.entity.EnrollmentStatus;
import com.lms.backend.enrollment.repository.EnrollmentRepository;
import com.lms.backend.user.entity.User;
import com.lms.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService{
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public EnrollmentResponse enroll(Long courseId) {

        User student = getCurrentUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        enrollmentRepository.findByStudentAndCourse(student, course)
                .ifPresent(enrollment -> {
                    throw new IllegalStateException(
                            "Already enrolled in this course");
                });

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .status(EnrollmentStatus.ACTIVE)
                .build();

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return mapToResponse(savedEnrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getMyEnrollments() {

        User student = getCurrentUser();

        return enrollmentRepository.findByStudent(student)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private EnrollmentResponse mapToResponse(
            Enrollment enrollment) {

        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .courseId(enrollment.getCourse().getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .status(enrollment.getStatus())
                .build();
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));
    }
}
