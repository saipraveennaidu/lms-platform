package com.lms.backend.quiz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.course.entity.Course;
import com.lms.backend.course.repository.CourseRepository;
import com.lms.backend.quiz.dto.CreateQuizRequest;
import com.lms.backend.quiz.dto.QuizResponse;
import com.lms.backend.quiz.entity.Quiz;
import com.lms.backend.quiz.repository.QuizRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService {
    
    private final QuizRepository quizRepository;
    private final CourseRepository courseRepository;

    @Override
    public QuizResponse createQuiz(Long courseId, CreateQuizRequest request) {
        Course course = courseRepository.findById(courseId).orElseThrow(
            () -> new ResourceNotFoundException("Course not found")
        );

        Quiz quiz = Quiz.builder()
                    .title(request.getTitle())
                    .course(course)
                    .build();

        return mapToResponse(quizRepository.save(quiz));
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponse> getQuizzesByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
            () -> new ResourceNotFoundException("Course not found")
        );

        return quizRepository.findByCourse(course)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
    }
    
    private QuizResponse mapToResponse(Quiz quiz) {
        return QuizResponse.builder()
                    .id(quiz.getId())
                    .title(quiz.getTitle())
                    .build();
    }
}
