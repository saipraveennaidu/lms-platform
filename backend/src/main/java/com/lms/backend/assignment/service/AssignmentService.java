package com.lms.backend.assignment.service;

import java.util.List;

import com.lms.backend.assignment.dto.AssignmentResponse;
import com.lms.backend.assignment.dto.CreateAssignmentRequest;

public interface AssignmentService {
    AssignmentResponse createAssignment(
            Long courseId,
            CreateAssignmentRequest request);

    List<AssignmentResponse> getAssignmentsByCourse(
            Long courseId);

}
