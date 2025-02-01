package com.hemnath.task.service;

import com.hemnath.task.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListID);
}
