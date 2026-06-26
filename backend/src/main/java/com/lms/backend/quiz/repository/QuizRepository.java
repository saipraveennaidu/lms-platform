package com.lms.backend.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.course.entity.Course;
import com.lms.backend.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{
    List<Quiz> findByCourse(Course course);
}
