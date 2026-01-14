package com.vipul.taskmanager.taskmanager.service.impl;

import com.vipul.taskmanager.taskmanager.dto.TaskRequestDto;
import com.vipul.taskmanager.taskmanager.dto.TaskResponseDto;
import com.vipul.taskmanager.taskmanager.entity.Task;
import com.vipul.taskmanager.taskmanager.mapper.TaskMapper;
import com.vipul.taskmanager.taskmanager.repository.TaskRepository;
import com.vipul.taskmanager.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto) {
        Task task = taskMapper.toEntity(requestDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new RuntimeException("Task not found");
        }

        return taskMapper.toDto(optionalTask.get());
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not foun with id: "+id));
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        // updates only allowed fields
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toDto(updatedTask);
    }

    @Override
    public TaskResponseDto markTaskCompleted(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setCompleted(true);

        Task updatedTask = taskRepository.save(task);

        return taskMapper.toDto(updatedTask);

    }
}