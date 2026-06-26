package com.lms.backend.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.assignment.dto.AssignmentResponse;
import com.lms.backend.assignment.dto.CreateAssignmentRequest;
import com.lms.backend.assignment.entity.Assignment;
import com.lms.backend.assignment.repository.AssignmentRepository;
import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.course.entity.Course;
import com.lms.backend.course.repository.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    @Override
    public AssignmentResponse createAssignment(Long courseId, CreateAssignmentRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        Assignment assignment = Assignment.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .course(course)
                .build();

        Assignment savedAssignment =
                assignmentRepository.save(assignment);

        return mapToResponse(savedAssignment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssignmentResponse> getAssignmentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        return assignmentRepository.findByCourse(course)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private AssignmentResponse mapToResponse(
            Assignment assignment) {

        return AssignmentResponse.builder()
                .id(assignment.getId())
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .dueDate(assignment.getDueDate())
                .build();
    }
}
