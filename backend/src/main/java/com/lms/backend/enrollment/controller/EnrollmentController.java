package com.lms.backend.enrollment.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.enrollment.dto.EnrollmentResponse;
import com.lms.backend.enrollment.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping("/courses/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public EnrollmentResponse enroll(
            @PathVariable Long courseId) {

        return enrollmentService.enroll(courseId);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('STUDENT')")
    public List<EnrollmentResponse> getMyEnrollments() {

        return enrollmentService.getMyEnrollments();
    }
}
