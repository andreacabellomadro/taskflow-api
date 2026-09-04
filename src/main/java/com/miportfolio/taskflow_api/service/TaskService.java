package com.miportfolio.taskflow_api.service;

import com.miportfolio.taskflow_api.dto.TaskRequestDTO;
import com.miportfolio.taskflow_api.dto.TaskResponseDTO;
import com.miportfolio.taskflow_api.entity.Task;
import com.miportfolio.taskflow_api.entity.TaskStatus;
import com.miportfolio.taskflow_api.exception.ResourceNotFoundException;
import com.miportfolio.taskflow_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAllTasks(TaskStatus status) {
        List<Task> tasks = (status != null) 
                ? taskRepository.findByStatus(status) 
                : taskRepository.findAll();
        return tasks.stream().map(this::mapToResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
        return mapToResponseDTO(task);
    }

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO dto) {
        Task task = Task.builder()
                .title(dto.title())
                .description(dto.description())
                .status(dto.status() != null ? dto.status() : TaskStatus.PENDING)
                .build();
        return mapToResponseDTO(taskRepository.save(task));
    }

    @Transactional
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        if (dto.status() != null) {
            task.setStatus(dto.status());
        }

        return mapToResponseDTO(taskRepository.save(task));
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: Tarea no encontrada con ID: " + id);
        }
        taskRepository.deleteById(id);
    }

    private TaskResponseDTO mapToResponseDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }
}