package com.lms.backend.progress.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.progress.dto.ProgressResponse;
import com.lms.backend.progress.service.ProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {
    private final ProgressService progressService;

    @PostMapping("/lessons/{lessonId}/complete")
    @PreAuthorize("hasRole('STUDENT')")
    public ProgressResponse markCompleted(
            @PathVariable Long lessonId) {

        return progressService.markCompleted(lessonId);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('STUDENT')")
    public List<ProgressResponse> getMyProgress() {

        return progressService.getMyProgress();
    }
}
