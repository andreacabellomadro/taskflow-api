package com.miportfolio.taskflow_api.dto;

import com.miportfolio.taskflow_api.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(
        @NotBlank(message = "El título no puede estar vacío") @Size(max = 100, message = "El título debe tener menos de 100 caracteres") String title,

        @Size(max = 500, message = "La descripción no puede exceder 500 caracteres") String description,

        TaskStatus status) {
}