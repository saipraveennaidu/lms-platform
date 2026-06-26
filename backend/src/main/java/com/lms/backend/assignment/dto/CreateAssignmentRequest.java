package com.lms.backend.assignment.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAssignmentRequest {
    
    @NotBlank
    private String title;

    private String description;

    private LocalDateTime dueDate;
}
