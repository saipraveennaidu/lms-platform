package com.lms.backend.course.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.course.dto.CourseResponse;
import com.lms.backend.course.dto.CreateCourseRequest;
import com.lms.backend.course.dto.UpdateCourseRequest;
import com.lms.backend.course.entity.Course;
import com.lms.backend.course.entity.CourseStatus;
import com.lms.backend.course.repository.CourseRepository;
import com.lms.backend.user.entity.User;
import com.lms.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {
        User instructor = getCurrentUser();

        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(CourseStatus.DRAFT)
                .instructor(instructor)
                .build();

        Course savedCourse = courseRepository.save(course);
        
        return mapToResponse(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        return mapToResponse(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getMyCourses() {
        User instructor = getCurrentUser();

        List<Course> courses = courseRepository.findByInstructor(instructor);

        return courses.stream()
                    .map(this::mapToResponse)
                    .toList();
    }

    

    @Override
    public CourseResponse updateCourse(Long courseId, UpdateCourseRequest request) {
        User currentUser = getCurrentUser();

        Course course = courseRepository.findById(courseId).orElseThrow(
            () -> new ResourceNotFoundException("Course not found")
        );

        if(!course.getInstructor().getId()
                .equals(currentUser.getId())) {
                    throw new AccessDeniedException(
                        "You are not allowed to update this course"
                    );
        }

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());

        return mapToResponse(course);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = getOwnedCourse(courseId);

        courseRepository.delete(course);
    }
    
    private CourseResponse mapToResponse(Course course) {
        return CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .description(course.getDescription())
                    .status(course.getStatus())
                    .instructorName(course.getInstructor().getName())
                    .build();
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(
            () -> new ResourceNotFoundException("User not found")
        );
    }

    private Course getOwnedCourse(Long courseId) {

        User currentUser = getCurrentUser();

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        if (!course.getInstructor()
                .getId()
                .equals(currentUser.getId())) {

            throw new AccessDeniedException(
                    "You are not allowed to access this course");
        }

        return course;
    }
}
