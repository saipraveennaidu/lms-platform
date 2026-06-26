package com.lms.backend.assignment.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.assignment.dto.AssignmentResponse;
import com.lms.backend.assignment.dto.CreateAssignmentRequest;
import com.lms.backend.assignment.service.AssignmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses/{courseId}/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public AssignmentResponse createAssignment(
            @PathVariable Long courseId,
            @Valid @RequestBody CreateAssignmentRequest request) {

        return assignmentService.createAssignment(
                courseId,
                request);
    }

    @GetMapping
    public List<AssignmentResponse> getAssignmentsByCourse(
            @PathVariable Long courseId) {

        return assignmentService.getAssignmentsByCourse(
                courseId);
    }
}
