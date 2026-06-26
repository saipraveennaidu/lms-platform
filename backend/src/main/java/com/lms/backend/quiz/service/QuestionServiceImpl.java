package com.lms.backend.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.quiz.dto.CreateQuestionRequest;
import com.lms.backend.quiz.dto.QuestionResponse;
import com.lms.backend.quiz.entity.Question;
import com.lms.backend.quiz.entity.Quiz;
import com.lms.backend.quiz.repository.QuestionRepository;
import com.lms.backend.quiz.repository.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Override
    public QuestionResponse createQuestion(Long quizId, CreateQuestionRequest request) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz not found"));

        Question question = Question.builder()
                .questionText(request.getQuestionText())
                .quiz(quiz)
                .build();

        Question savedQuestion = questionRepository.save(question);

        return mapToResponse(savedQuestion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponse> getQuestions(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz not found"));

        return questionRepository.findByQuiz(quiz)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private QuestionResponse mapToResponse(Question question) {

        return QuestionResponse.builder()
                .id(question.getId())
                .questionText(question.getQuestionText())
                .build();
    }
    
}
