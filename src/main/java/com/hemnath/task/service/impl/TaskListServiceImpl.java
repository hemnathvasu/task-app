package com.hemnath.task.service.impl;

import com.hemnath.task.model.TaskList;
import com.hemnath.task.repository.TaskListRepository;
import com.hemnath.task.service.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;


    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> getAllTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if(taskList.getId() !=null){
            throw new IllegalArgumentException("Task list already has a ID");
        }

        if(taskList.getTitle() == null || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task list title must be present");
        }
        LocalDateTime now = LocalDateTime.now();

        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID id, TaskList taskList) {
        if(taskList.getId() == null){
            throw new IllegalArgumentException("Task list must a ID!");
        }

        if(!Objects.equals(id,taskList.getId())){
            throw new IllegalArgumentException("Not permitted to change the another task list");
        }

        TaskList updateTaskList = taskListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Task list does not exist"));

        updateTaskList.setDescription(taskList.getDescription());
        updateTaskList.setTitle(taskList.getTitle());
        updateTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(updateTaskList);
    }

    @Override
    public void deleteTastList(UUID id) {
        if(id == null){
            throw new IllegalArgumentException("Task list must a ID!");
        }

        TaskList taskList = taskListRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Task list does not exist"));

        taskListRepository.deleteById(id);
    }
}
