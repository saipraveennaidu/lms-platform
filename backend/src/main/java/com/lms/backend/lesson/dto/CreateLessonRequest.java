package com.lms.backend.lesson.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLessonRequest {
    @NotBlank
    private String title;
    private String content;
    private String videoUrl;
    private Integer orderNumber;
}
