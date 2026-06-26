package com.lms.backend.progress.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.lesson.entity.Lesson;
import com.lms.backend.lesson.repository.LessonRepository;
import com.lms.backend.progress.dto.ProgressResponse;
import com.lms.backend.progress.entity.Progress;
import com.lms.backend.progress.repository.ProgressRepository;
import com.lms.backend.user.entity.User;
import com.lms.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgressServiceImpl implements ProgressService{
    private final ProgressRepository progressRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Override
    public ProgressResponse markCompleted(Long lessonId) {

        User student = getCurrentUser();

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lesson not found"));

        Progress progress = progressRepository
                .findByStudentAndLesson(student, lesson)
                .orElse(
                        Progress.builder()
                                .student(student)
                                .lesson(lesson)
                                .build());

        progress.setCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());

        Progress savedProgress =
                progressRepository.save(progress);

        return mapToResponse(savedProgress);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProgressResponse> getMyProgress() {

        User student = getCurrentUser();

        return progressRepository.findByStudent(student)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProgressResponse mapToResponse(Progress progress) {

        return ProgressResponse.builder()
                .lessonId(progress.getLesson().getId())
                .lessonTitle(progress.getLesson().getTitle())
                .completed(progress.getCompleted())
                .build();
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));
    }
}
