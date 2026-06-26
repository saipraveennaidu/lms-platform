package com.lms.backend.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.quiz.entity.Option;
import com.lms.backend.quiz.entity.Question;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestion(Question question);
}
