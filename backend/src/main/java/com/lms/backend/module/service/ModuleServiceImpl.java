package com.lms.backend.module.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.backend.common.exception.ResourceNotFoundException;
import com.lms.backend.course.entity.Course;
import com.lms.backend.course.repository.CourseRepository;
import com.lms.backend.module.dto.CreateModuleRequest;
import com.lms.backend.module.dto.ModuleResponse;
import com.lms.backend.module.entity.Module;
import com.lms.backend.module.repository.ModuleRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    @Override
    public ModuleResponse createModule(Long courseId, CreateModuleRequest request) {
        Course course = courseRepository.findById(courseId).orElseThrow(
            () -> new ResourceNotFoundException("Course not found")
        );

        Module module = Module.builder()
                .title(request.getTitle())
                .orderNumber(request.getOrderNumber())
                .course(course)
                .build();

        Module savedModule = moduleRepository.save(module);

        return mapToResponse(savedModule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModuleResponse> getModulesByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
            () -> new ResourceNotFoundException("Course not found")
        );

        return moduleRepository.findByCourse(course)
                    .stream()
                    .map(this::mapToResponse)
                    .toList();
    }
    
    private ModuleResponse mapToResponse(Module module) {

        return ModuleResponse.builder()
                .id(module.getId())
                .title(module.getTitle())
                .orderNumber(module.getOrderNumber())
                .build();
    }
}
