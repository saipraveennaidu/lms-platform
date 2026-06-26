package com.lms.backend.quiz.service;

import java.util.List;

import com.lms.backend.quiz.dto.CreateOptionRequest;
import com.lms.backend.quiz.dto.OptionResponse;

public interface OptionService {
    OptionResponse createOption(
        Long questionId,
        CreateOptionRequest request);

    List<OptionResponse> getOptions(
        Long questionId);
}
