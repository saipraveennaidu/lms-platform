package com.lms.backend.progress.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.lesson.entity.Lesson;
import com.lms.backend.progress.entity.Progress;
import com.lms.backend.user.entity.User;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByStudentAndLesson(
            User student,
            Lesson lesson);

    List<Progress> findByStudent(User student);
}
