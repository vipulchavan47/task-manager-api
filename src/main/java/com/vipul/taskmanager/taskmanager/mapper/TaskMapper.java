package com.vipul.taskmanager.taskmanager.mapper;

import com.vipul.taskmanager.taskmanager.dto.TaskRequestDto;
import com.vipul.taskmanager.taskmanager.dto.TaskResponseDto;
import com.vipul.taskmanager.taskmanager.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", constant = "false")
    Task toEntity(TaskRequestDto dto);

    TaskResponseDto toDto(Task task);
}