package com.lms.backend.quiz.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionResponse {
    private Long id;
    private String questionText;
}
