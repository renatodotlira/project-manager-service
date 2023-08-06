package com.agnes.pm.translate;

import com.agnes.pm.dto.ProjectActivityDTO;
import com.agnes.pm.dto.ProjectDTO;
import com.agnes.pm.model.Project;
import com.agnes.pm.resource.ProjectResource;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

public class ProjectTranslator {

    public static Project toModel(@NotNull final ProjectResource from) {

        return Project.builder()
                .name(from.getName())
                .client(from.getClient() != null ? ClientTranslator.toModel(from.getClient()) : null)
                .status(from.getStatus())
                .build();
    }

    public static Project toModel(@NotNull final ProjectDTO from) {

        return Project.builder()
                .id(from.getId())
                .name(from.getName())
                .client(from.getClient() != null ? ClientTranslator.toModel(from.getClient()) : null)
                .status(from.getStatus())
                .build();
    }

    public static Project toModel(@NotNull final ProjectActivityDTO from) {

        return Project.builder()
                .id(from.getId())
                .name(from.getName())
                .client(from.getClient() != null ? ClientTranslator.toModel(from.getClient()) : null)
                .status(from.getStatus())
                .build();
    }

    public static Project toEntity(@NotNull final ProjectResource from, @NotNull Long id) {

        return Project.builder()
                .id(id)
                .name(from.getName())
                .client(ClientTranslator.toModel(from.getClient()))
                .status(from.getStatus())
                .build();
    }

    public static ProjectDTO toDto(@NotNull final Project from){

        return ProjectDTO.builder()
                .id(from.getId())
                .name(from.getName())
                .client(ClientTranslator.toDto(from.getClient()))
                .activities(from.getActivities().stream().map(ActivityTranslator::toActivityProjectDto).collect(Collectors.toList()))
                .status(from.getStatus())
                .build();
    }

    public static ProjectActivityDTO toProjectActivityDto(@NotNull final Project from){

        return ProjectActivityDTO.builder()
                .id(from.getId())
                .name(from.getName())
                .client(ClientTranslator.toDto(from.getClient()))
                .status(from.getStatus())
                .build();
    }

}
