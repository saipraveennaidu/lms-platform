package com.lms.backend.assignment.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.assignment.dto.SubmissionResponse;
import com.lms.backend.assignment.dto.SubmitAssignmentRequest;
import com.lms.backend.assignment.service.SubmissionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService submissionService;

    @PostMapping("/{assignmentId}/submit")
    @PreAuthorize("hasRole('STUDENT')")
    public SubmissionResponse submitAssignment(
            @PathVariable Long assignmentId,
            @Valid @RequestBody SubmitAssignmentRequest request) {

        return submissionService.submitAssignment(
                assignmentId,
                request);
    }

    @GetMapping("/my-submissions")
    @PreAuthorize("hasRole('STUDENT')")
    public List<SubmissionResponse> getMySubmissions() {

        return submissionService.getMySubmissions();
    }
}
