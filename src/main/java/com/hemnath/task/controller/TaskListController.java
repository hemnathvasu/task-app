package com.hemnath.task.controller;

import com.hemnath.task.dto.TaskListDto;
import com.hemnath.task.mapper.TaskListMapper;
import com.hemnath.task.model.TaskList;
import com.hemnath.task.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> getAllTaskList(){
        return taskListService.getAllTaskList()
                .stream()
                .map(taskListMapper::taskToDto)
                .toList();
    }

    @PostMapping
    public TaskListDto saveTaskList(@RequestBody TaskListDto taskListDto){
        TaskList taskList = taskListService.createTaskList(taskListMapper.dtoToTask(taskListDto));
        return taskListMapper.taskToDto(taskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID id){
        return taskListService.getTaskList(id).map(taskListMapper::taskToDto);  
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDto update(@PathVariable("task_list_id") UUID id,@RequestBody TaskListDto taskListDto){
        TaskList taskList = taskListService.updateTaskList(id,taskListMapper.dtoToTask(taskListDto));
        return taskListMapper.taskToDto(taskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID id){
        taskListService.deleteTastList(id);
    }
}
