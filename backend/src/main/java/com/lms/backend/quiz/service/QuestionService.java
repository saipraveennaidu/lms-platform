package com.lms.backend.quiz.service;

import java.util.List;

import com.lms.backend.quiz.dto.CreateQuestionRequest;
import com.lms.backend.quiz.dto.QuestionResponse;

public interface QuestionService {
    QuestionResponse createQuestion(
            Long quizId,
            CreateQuestionRequest request);
    
    List<QuestionResponse> getQuestions(
            Long quizId);
}
