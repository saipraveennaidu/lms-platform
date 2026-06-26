package com.lms.backend.enrollment.service;

import java.util.List;

import com.lms.backend.enrollment.dto.EnrollmentResponse;

public interface EnrollmentService {
    EnrollmentResponse enroll(Long courseId);

    List<EnrollmentResponse> getMyEnrollments();
}
