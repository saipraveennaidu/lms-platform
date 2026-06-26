package com.lms.backend.lesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.lesson.entity.Lesson;
import com.lms.backend.module.entity.Module;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByModule(Module module);
}
