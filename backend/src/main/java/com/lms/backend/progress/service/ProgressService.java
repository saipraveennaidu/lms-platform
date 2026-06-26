package com.lms.backend.progress.service;

import java.util.List;

import com.lms.backend.progress.dto.ProgressResponse;

public interface ProgressService {
    ProgressResponse markCompleted(
                Long lessonId);
    
        List<ProgressResponse> getMyProgress();
}
