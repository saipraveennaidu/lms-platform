package com.lms.backend.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionRequest {
    
    @NotBlank
    private String questionText;
}
