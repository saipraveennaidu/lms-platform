package com.lms.backend.lesson.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLessonRequest {
    private String title;

    private String content;

    private String videoUrl;

    private Integer orderNumber;
}
