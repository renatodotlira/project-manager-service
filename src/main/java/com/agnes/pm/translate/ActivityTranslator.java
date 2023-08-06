package com.agnes.pm.translate;

import com.agnes.pm.dto.ActivityDTO;
import com.agnes.pm.dto.ActivityProjectDTO;
import com.agnes.pm.model.Activity;
import com.agnes.pm.resource.ActivityResource;

import javax.validation.constraints.NotNull;

public class ActivityTranslator {

    public static Activity toModel(@NotNull final ActivityResource from) {

        return Activity.builder()
                .title(from.getTitle())
                .description(from.getDescription())
                .project(ProjectTranslator.toModel(from.getProject()))
                .status(from.getStatus())
                .build();
    }

    public static Activity toModel(@NotNull final ActivityDTO from) {

        return Activity.builder()
                .id(from.getId())
                .title(from.getTitle())
                .description(from.getDescription())
                .project(ProjectTranslator.toModel(from.getProject()))
                .status(from.getStatus())
                .build();
    }

    public static Activity toEntity(@NotNull final ActivityResource from, @NotNull Long id) {

        return Activity.builder()
                .id(id)
                .title(from.getTitle())
                .description(from.getDescription())
                .project(ProjectTranslator.toModel(from.getProject()))
                .status(from.getStatus())
                .build();
    }

    public static ActivityDTO toDto(@NotNull final Activity from){

        return ActivityDTO.builder()
                .id(from.getId())
                .title(from.getTitle())
                .description(from.getDescription())
                .project(ProjectTranslator.toProjectActivityDto(from.getProject()))
                .status(from.getStatus())
                .build();
    }

    public static ActivityProjectDTO toActivityProjectDto(@NotNull final Activity from){

        return ActivityProjectDTO.builder()
                .id(from.getId())
                .title(from.getTitle())
                .description(from.getDescription())
                .status(from.getStatus())
                .build();
    }

}
