package com.lms.backend.quiz.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizResponse {
    private Long id;
    private String title;
}
