package com.lms.backend.quiz.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.quiz.dto.CreateQuizRequest;
import com.lms.backend.quiz.dto.QuizResponse;
import com.lms.backend.quiz.service.QuizService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses/{courseId}/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public QuizResponse createQuiz(@PathVariable Long courseId, @Valid @RequestBody CreateQuizRequest request) {
        return quizService.createQuiz(courseId, request);
    }

    @GetMapping
    public List<QuizResponse> getQuizzesByCourse(@PathVariable Long courseId) {
        return quizService.getQuizzesByCourse(courseId);
    }
}
