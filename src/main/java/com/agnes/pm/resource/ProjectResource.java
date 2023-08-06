package com.agnes.pm.resource;

import com.agnes.pm.dto.ClientDTO;
import com.agnes.pm.model.types.ProjectStatus;
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
public class ProjectResource {

    @JsonProperty(value="name", required = true)
    @NotNull(message="project.mane.required")
    private String name;

    @JsonProperty(value="client", required = true)
    @NotNull(message="project.client.required")
    private ClientDTO client;

    @JsonProperty(value="status", required = true)
    @NotNull(message="project.status.required")
    private ProjectStatus status;

}
