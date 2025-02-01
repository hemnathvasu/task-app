package com.hemnath.task.controller;

import com.hemnath.task.dto.TaskDto;
import com.hemnath.task.dto.TaskListDto;
import com.hemnath.task.mapper.TaskListMapper;
import com.hemnath.task.mapper.TaskMapper;
import com.hemnath.task.model.Task;
import com.hemnath.task.model.TaskList;
import com.hemnath.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }


    @GetMapping
    public List<TaskDto> getTaskList(@PathVariable("task_list_id") UUID id){
        return taskService.listTasks(id)
                .stream()
                .map(taskMapper::taskToDto)
                .toList();
    }
    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID id,@RequestBody TaskDto taskDto){
        Task task = taskService.createTask(id,taskMapper.dtoToTask(taskDto));
        return taskMapper.taskToDto(task);
    }

    @GetMapping(path = "/{taskId}")
    public Optional<TaskDto> getTask(
            @PathVariable("task_list_id") UUID taskListId,
            @PathVariable("taskId") UUID id){
        return taskService.getTask(taskListId,id).map(taskMapper::taskToDto);
    }

    @PutMapping(path = "/{taskId}")
    public TaskDto update(@PathVariable("taskId") UUID id,@RequestBody TaskDto taskDto){
        Task task = taskService.updateTask(id,taskMapper.dtoToTask(taskDto));
        return taskMapper.taskToDto(task);
    }

    @DeleteMapping(path = "/{taskId}")
    public void deleteTaskList(@PathVariable("taskId") UUID id){
        taskService.delete(id);
    }
}
