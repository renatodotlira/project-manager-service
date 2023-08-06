package com.agnes.pm.resource;

import com.agnes.pm.dto.ProjectActivityDTO;
import com.agnes.pm.dto.ProjectDTO;
import com.agnes.pm.model.types.ActivityStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResource {

    @JsonProperty(value="title", required = true)
    @NotNull(message="activity.title.required")
    private String title;

    private String description;

    @JsonProperty(value="project", required = true)
    @NotNull(message="activity.project.required")
    private ProjectActivityDTO project;

    @JsonProperty(value="status", required = true)
    @NotNull(message="project.status.required")
    private ActivityStatus status;

}
