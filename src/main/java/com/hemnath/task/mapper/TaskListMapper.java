package com.hemnath.task.mapper;

import com.hemnath.task.dto.TaskDto;
import com.hemnath.task.dto.TaskListDto;
import com.hemnath.task.model.Task;
import com.hemnath.task.model.TaskList;

public interface TaskListMapper {
    TaskList dtoToTask(TaskListDto taskListDto);
    TaskListDto taskToDto(TaskList taskList);
}
