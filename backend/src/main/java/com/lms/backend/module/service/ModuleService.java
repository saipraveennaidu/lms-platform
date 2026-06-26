package com.lms.backend.module.service;

import java.util.List;

import com.lms.backend.module.dto.CreateModuleRequest;
import com.lms.backend.module.dto.ModuleResponse;

import jakarta.validation.Valid;

public interface ModuleService {
    ModuleResponse createModule(
            Long courseId,
            @Valid CreateModuleRequest request);

    List<ModuleResponse> getModulesByCourse(
            Long courseId);
}
