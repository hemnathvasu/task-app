package com.hemnath.task.service.impl;

import com.hemnath.task.model.Task;
import com.hemnath.task.model.TaskList;
import com.hemnath.task.model.TaskPriority;
import com.hemnath.task.model.TaskStatus;
import com.hemnath.task.repository.TaskListRepository;
import com.hemnath.task.repository.TaskRepository;
import com.hemnath.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListID) {
        return taskRepository.findByTaskListId(taskListID);
    }

    @Override
    public Task createTask(UUID taskListID,Task task) {
        if(task.getId() !=null){
            throw new IllegalArgumentException("Task already has a ID");
        }

        if(task.getTitle() == null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title must be present");
        }
        LocalDateTime now = LocalDateTime.now();

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListID)
                .orElseThrow(()-> new IllegalArgumentException("Invalid Task List ID"));
        LocalDateTime now1 = LocalDateTime.now();

        Task saveTask = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                now1,
                now1,
                taskList
        );
        return taskRepository.save(saveTask);
    }

    @Override
    public Optional<Task> getTask(UUID taskListID, UUID id) {
        return taskRepository.findByTaskListIdAndId(taskListID,id);
    }

    @Override
    public Task updateTask(UUID id, Task task) {
        if(task.getId() == null){
            throw new IllegalArgumentException("Task list must a ID!");
        }

        if(!Objects.equals(id,task.getId())){
            throw new IllegalArgumentException("Not permitted to change the another task list");
        }

        Task updateTask = taskRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("No task found"));

        updateTask.setDescription(task.getDescription());
        updateTask.setTitle(task.getTitle());
        updateTask.setUpdated(LocalDateTime.now());
        updateTask.setDueDate(task.getDueDate());
        updateTask.setPriority(task.getPriority());
        updateTask.setStatus(task.getStatus());
        return taskRepository.save(updateTask);
    }

    @Override
    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }
}
