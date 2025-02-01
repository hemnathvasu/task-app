package com.hemnath.task.mapper;

import com.hemnath.task.dto.TaskListDto;
import com.hemnath.task.model.Task;
import com.hemnath.task.model.TaskList;
import com.hemnath.task.model.TaskStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper{

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList dtoToTask(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks-> tasks.stream()
                                .map(taskMapper::dtoToTask)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto taskToDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks ->
                                tasks.stream().map(taskMapper::taskToDto).toList()
                        ).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(tasks == null){
            return  null;
        }

        long cloesedTaskCount = tasks.stream()
                .filter(task -> TaskStatus.CLOSED == task.getStatus())
                .count();

        return  (double) cloesedTaskCount/tasks.size();
    }
}
