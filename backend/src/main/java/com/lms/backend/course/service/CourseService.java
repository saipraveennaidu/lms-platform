package com.lms.backend.course.service;

import java.util.List;

import com.lms.backend.course.dto.CourseResponse;
import com.lms.backend.course.dto.CreateCourseRequest;
import com.lms.backend.course.dto.UpdateCourseRequest;

public interface CourseService {
    CourseResponse createCourse(CreateCourseRequest request);
    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(Long courseId);
    List<CourseResponse> getMyCourses();
    CourseResponse updateCourse(
        Long courseId,
        UpdateCourseRequest request);
    void deleteCourse(Long courseId);
}
