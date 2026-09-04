package com.miportfolio.taskflow_api.repository;

import com.miportfolio.taskflow_api.entity.Task;
import com.miportfolio.taskflow_api.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
}
