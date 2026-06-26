package com.lms.backend.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.quiz.entity.Question;
import com.lms.backend.quiz.entity.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);
}
