package com.lms.backend.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.quiz.dto.CreateOptionRequest;
import com.lms.backend.quiz.dto.OptionResponse;
import com.lms.backend.quiz.entity.Option;
import com.lms.backend.quiz.entity.Question;
import com.lms.backend.quiz.repository.OptionRepository;
import com.lms.backend.quiz.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OptionServiceImpl implements OptionService{
    
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    @Override
    public OptionResponse createOption(Long questionId, CreateOptionRequest request) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found"));

        Option option = Option.builder()
                .optionText(request.getOptionText())
                .correct(request.getCorrect())
                .question(question)
                .build();

        Option savedOption = optionRepository.save(option);

        return mapToResponse(savedOption);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptionResponse> getOptions(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found"));

        return optionRepository.findByQuestion(question)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OptionResponse mapToResponse(Option option) {

        return OptionResponse.builder()
                .id(option.getId())
                .optionText(option.getOptionText())
                .correct(option.getCorrect())
                .build();
    }
    
}
