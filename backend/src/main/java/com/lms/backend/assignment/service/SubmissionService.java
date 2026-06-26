package com.lms.backend.assignment.service;

import java.util.List;

import com.lms.backend.assignment.dto.SubmissionResponse;
import com.lms.backend.assignment.dto.SubmitAssignmentRequest;

public interface SubmissionService {
    SubmissionResponse submitAssignment(
            Long assignmentId,
            SubmitAssignmentRequest request);

    List<SubmissionResponse> getMySubmissions();

}
