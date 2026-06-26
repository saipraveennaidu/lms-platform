package com.lms.backend.module.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateModuleRequest {
    @NotBlank
    private String title;

    private Integer orderNumber;
}
