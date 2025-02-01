package com.hemnath.task.mapper;

import com.hemnath.task.dto.TaskDto;
import com.hemnath.task.model.Task;

public interface TaskMapper {
    Task dtoToTask(TaskDto taskDto);
    TaskDto taskToDto(Task task);
}
