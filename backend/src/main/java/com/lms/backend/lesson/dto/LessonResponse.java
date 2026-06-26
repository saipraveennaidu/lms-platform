package com.lms.backend.lesson.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LessonResponse {
    private Long id;
    private String title;
    private String content;
    private String videoUrl;
    private Integer orderNumber;
}
