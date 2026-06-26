package com.lms.backend.quiz.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.quiz.dto.CreateQuestionRequest;
import com.lms.backend.quiz.dto.QuestionResponse;
import com.lms.backend.quiz.service.QuestionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public QuestionResponse createQuestion(
            @PathVariable Long quizId,
            @Valid @RequestBody CreateQuestionRequest request) {

        return questionService.createQuestion(
                quizId,
                request);
    }

    @GetMapping
    public List<QuestionResponse> getQuestions(
            @PathVariable Long quizId) {

        return questionService.getQuestions(quizId);
    }
}
