package com.miportfolio.taskflow_api.dto;

import com.miportfolio.taskflow_api.entity.TaskStatus;
import java.time.LocalDateTime;

public record TaskResponseDTO(
    Long id,
    String title,
    String description,
    TaskStatus status,
    LocalDateTime createdAt
) {}
