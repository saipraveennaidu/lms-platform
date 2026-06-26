package com.lms.backend.course.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.course.dto.CourseResponse;
import com.lms.backend.course.dto.CreateCourseRequest;
import com.lms.backend.course.dto.UpdateCourseRequest;
import com.lms.backend.course.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public CourseResponse createCourse(@Valid @RequestBody CreateCourseRequest request) {
        return courseService.createCourse(request);
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public CourseResponse updateCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody UpdateCourseRequest request) {
        return courseService.updateCourse(courseId, request);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public CourseResponse getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("my-courses")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTRUCTOR')")
    public List<CourseResponse> getMyCourses() {
        return courseService.getMyCourses();
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public void deleteCourse(@PathVariable Long courseId) {

        courseService.deleteCourse(courseId);
    }
}
