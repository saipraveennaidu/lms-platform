package com.lms.backend.enrollment.dto;

import com.lms.backend.enrollment.entity.EnrollmentStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnrollmentResponse {
    private Long id;

    private Long courseId;

    private String courseTitle;

    private EnrollmentStatus status;
}
