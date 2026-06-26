package com.lms.backend.assignment.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.assignment.dto.SubmissionResponse;
import com.lms.backend.assignment.dto.SubmitAssignmentRequest;
import com.lms.backend.assignment.entity.Assignment;
import com.lms.backend.assignment.entity.Submission;
import com.lms.backend.assignment.repository.AssignmentRepository;
import com.lms.backend.assignment.repository.SubmissionRepository;
import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.user.entity.User;
import com.lms.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmissionServiceImpl implements SubmissionService{

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    @Override
    public SubmissionResponse submitAssignment(Long assignmentId, SubmitAssignmentRequest request) {
        User student = getCurrentUser();

        Assignment assignment =
                assignmentRepository.findById(assignmentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Assignment not found"));

        Submission submission = Submission.builder()
                .submissionText(request.getSubmissionText())
                .assignment(assignment)
                .student(student)
                .build();

        Submission savedSubmission =
                submissionRepository.save(submission);

        return mapToResponse(savedSubmission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubmissionResponse> getMySubmissions() {
        User student = getCurrentUser();

        return submissionRepository.findByStudent(student)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    private SubmissionResponse mapToResponse(
            Submission submission) {

        return SubmissionResponse.builder()
                .id(submission.getId())
                .submissionText(submission.getSubmissionText())
                .submittedAt(submission.getSubmittedAt())
                .build();
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));
    }
}
