package com.hemnath.task.dto;

import com.hemnath.task.model.TaskStatus;

import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        String dueDate,
        String priority,
        TaskStatus status
) {
}
