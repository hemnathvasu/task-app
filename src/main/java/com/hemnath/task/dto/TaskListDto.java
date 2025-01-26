package com.hemnath.task.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        String count,
        Double progress,
        List<TaskDto> tasks
) {
}
