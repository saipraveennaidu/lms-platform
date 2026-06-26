package com.lms.backend.assignment.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssignmentResponse {
    private Long id;

    private String title;

    private String description;

    private LocalDateTime dueDate;
}
