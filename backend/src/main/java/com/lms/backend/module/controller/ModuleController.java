package com.lms.backend.module.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lms.backend.module.dto.CreateModuleRequest;
import com.lms.backend.module.dto.ModuleResponse;
import com.lms.backend.module.service.ModuleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses/{courseId}/modules")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleService moduleService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ModuleResponse createModule(@PathVariable Long courseId, @Valid @RequestBody CreateModuleRequest  request) {
        return moduleService.createModule(courseId, request);
    }

    @GetMapping
    public List<ModuleResponse> getModulesByCourse(
            @PathVariable Long courseId) {

        return moduleService.getModulesByCourse(
                courseId);
    }
}
