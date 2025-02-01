package com.hemnath.task.service;

import com.hemnath.task.model.Task;
import com.hemnath.task.model.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListID);
    Task createTask(UUID taskListID,Task task);
    Optional<Task> getTask(UUID taskListID,UUID id);
    Task updateTask(UUID id, Task task);
    void delete(UUID id);
}
