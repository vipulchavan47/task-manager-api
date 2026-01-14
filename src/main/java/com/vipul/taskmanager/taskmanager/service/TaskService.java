package com.vipul.taskmanager.taskmanager.service;

import com.vipul.taskmanager.taskmanager.dto.TaskRequestDto;
import com.vipul.taskmanager.taskmanager.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto requestDto);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto getTaskById(Long id);

    void deleteTask(Long id);

    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);

    TaskResponseDto markTaskCompleted(Long id);
}
