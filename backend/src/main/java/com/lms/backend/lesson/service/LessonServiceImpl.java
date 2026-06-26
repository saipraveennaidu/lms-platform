package com.lms.backend.lesson.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.lesson.dto.CreateLessonRequest;
import com.lms.backend.lesson.dto.LessonResponse;
import com.lms.backend.lesson.dto.UpdateLessonRequest;
import com.lms.backend.lesson.entity.Lesson;
import com.lms.backend.lesson.repository.LessonRepository;
import com.lms.backend.module.entity.Module;
import com.lms.backend.module.repository.ModuleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public LessonResponse createLesson(Long moduleId, CreateLessonRequest request) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(
            () -> new ResourceNotFoundException("Module not found")
        );

        Lesson lesson = Lesson.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .videoUrl(request.getVideoUrl())
                .orderNumber(request.getOrderNumber())
                .module(module)
                .build();

        Lesson savedLesson = lessonRepository.save(lesson);

        return mapToResponse(savedLesson);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonResponse> getLessonsByModule(Long moduleId) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(
            () -> new ResourceNotFoundException("Module not found")
        );

        return lessonRepository.findByModule(module)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
    }

    @Override
    public LessonResponse updateLesson(
            Long lessonId,
            UpdateLessonRequest request) {
    
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lesson not found"));
    
        lesson.setTitle(request.getTitle());
        lesson.setContent(request.getContent());
        lesson.setVideoUrl(request.getVideoUrl());
        lesson.setOrderNumber(request.getOrderNumber());
    
        return mapToResponse(lesson);
    }

    @Override
    public void deleteLesson(Long lessonId) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lesson not found"));

        lessonRepository.delete(lesson);
}

    private LessonResponse mapToResponse(Lesson lesson) {

        return LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .videoUrl(lesson.getVideoUrl())
                .orderNumber(lesson.getOrderNumber())
                .build();
    }
    
}
