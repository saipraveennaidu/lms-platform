package com.lms.backend.quiz.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.quiz.dto.CreateOptionRequest;
import com.lms.backend.quiz.dto.OptionResponse;
import com.lms.backend.quiz.service.OptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/questions/{questionId}/options")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public OptionResponse createOption(
            @PathVariable Long questionId,
            @Valid @RequestBody CreateOptionRequest request) {

        return optionService.createOption(
                questionId,
                request);
    }

    @GetMapping
    public List<OptionResponse> getOptions(
            @PathVariable Long questionId) {

        return optionService.getOptions(questionId);
    }
}
