package com.hemnath.task.service;

import com.hemnath.task.model.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> getAllTaskList();
    TaskList createTaskList(TaskList taskList);
    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID id, TaskList taskList);
    void deleteTastList(UUID id);
}
