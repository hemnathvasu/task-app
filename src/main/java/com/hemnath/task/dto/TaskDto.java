package com.hemnath.task.dto;

import com.hemnath.task.model.TaskPriority;
import com.hemnath.task.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
