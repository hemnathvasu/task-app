package com.hemnath.task.repository;

import com.hemnath.task.model.Task;
import com.hemnath.task.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskList, UUID> {
    List<TaskList> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListId,UUID id);
}
