package com.lms.backend.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseRequest {
    
    @NotBlank(message = "Titile is required")
    private String title;

    private String description;
}
