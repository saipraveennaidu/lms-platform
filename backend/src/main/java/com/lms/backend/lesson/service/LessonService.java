package com.lms.backend.lesson.service;

import java.util.List;

import com.lms.backend.lesson.dto.CreateLessonRequest;
import com.lms.backend.lesson.dto.LessonResponse;
import com.lms.backend.lesson.dto.UpdateLessonRequest;

public interface LessonService {
    LessonResponse createLesson(
            Long moduleId,
            CreateLessonRequest request);

    List<LessonResponse> getLessonsByModule(
            Long moduleId);

            LessonResponse updateLesson(
                    Long lessonId,
                    UpdateLessonRequest request);
            
            void deleteLesson(Long lessonId);
}
