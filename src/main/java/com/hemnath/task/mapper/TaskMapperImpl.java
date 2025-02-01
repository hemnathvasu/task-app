package com.hemnath.task.mapper;

import com.hemnath.task.dto.TaskDto;
import com.hemnath.task.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper{
    @Override
    public Task dtoToTask(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto taskToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
