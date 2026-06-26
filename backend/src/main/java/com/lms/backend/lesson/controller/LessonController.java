package com.lms.backend.lesson.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.lesson.dto.CreateLessonRequest;
import com.lms.backend.lesson.dto.LessonResponse;
import com.lms.backend.lesson.dto.UpdateLessonRequest;
import com.lms.backend.lesson.service.LessonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/modules/{moduleId}/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public LessonResponse createLesson(@PathVariable Long moduleId, @Valid @RequestBody CreateLessonRequest request) {
        return lessonService.createLesson(moduleId, request);
    }

    @GetMapping
    public List<LessonResponse> getLessonsByModule(@PathVariable Long moduleId) {
        return lessonService.getLessonsByModule(moduleId);
    }

    @PutMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public LessonResponse updateLesson(
            @PathVariable Long lessonId,
            @Valid @RequestBody UpdateLessonRequest request) {
    
        return lessonService.updateLesson(
                lessonId,
                request);
    }
    
    @DeleteMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public void deleteLesson(
            @PathVariable Long lessonId) {
    
        lessonService.deleteLesson(lessonId);
    }
}
