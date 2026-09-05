package com.miportfolio.taskflow_api.controller;

import com.miportfolio.taskflow_api.dto.TaskRequestDTO;
import com.miportfolio.taskflow_api.dto.TaskResponseDTO;
import com.miportfolio.taskflow_api.entity.TaskStatus;
import com.miportfolio.taskflow_api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Endpoints para la gestión del ciclo de vida de tareas")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Listar todas las tareas (filtro opcional por estado)")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(
            @RequestParam(required = false) TaskStatus status) {
        return ResponseEntity.ok(taskService.getAllTasks(status));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tarea por su ID")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una nueva tarea")
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una tarea existente")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long id, 
            @Valid @RequestBody TaskRequestDTO dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una tarea")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}