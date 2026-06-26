package com.lms.backend.progress.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProgressResponse {
    private Long lessonId;

    private String lessonTitle;

    private Boolean completed;
}
