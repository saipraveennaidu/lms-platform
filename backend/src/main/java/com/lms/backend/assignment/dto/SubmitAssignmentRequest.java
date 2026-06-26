package com.lms.backend.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmitAssignmentRequest {
    @NotBlank
    private String submissionText;
}
