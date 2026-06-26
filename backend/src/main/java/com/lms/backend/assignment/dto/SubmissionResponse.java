package com.lms.backend.assignment.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubmissionResponse {
    private Long id;

    private String submissionText;

    private LocalDateTime submittedAt;
}
