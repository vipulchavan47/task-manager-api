package com.vipul.taskmanager.taskmanager.controller;

import com.vipul.taskmanager.taskmanager.dto.TaskRequestDto;
import com.vipul.taskmanager.taskmanager.dto.TaskResponseDto;
import com.vipul.taskmanager.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto createTask(@RequestBody TaskRequestDto requestDto) {
        return taskService.createTask(requestDto);
    }

    @GetMapping()
    public List<TaskResponseDto> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTask(@PathVariable Long id, @RequestBody TaskRequestDto requestDto){
        return taskService.updateTask(id,requestDto);
    }

    @PatchMapping("/{id}/complete")
    public TaskResponseDto markTaskCompleted(@PathVariable Long id) {
        return taskService.markTaskCompleted(id);
    }


}
