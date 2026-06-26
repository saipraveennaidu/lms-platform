package com.lms.backend.quiz.service;

import java.util.List;

import com.lms.backend.quiz.dto.CreateQuizRequest;
import com.lms.backend.quiz.dto.QuizResponse;

public interface QuizService {
    QuizResponse createQuiz(
                Long courseId,
                CreateQuizRequest request);
    
    List<QuizResponse> getQuizzesByCourse(
                Long courseId);
}
