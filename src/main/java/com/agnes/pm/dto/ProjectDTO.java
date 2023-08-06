package com.agnes.pm.dto;

import com.agnes.pm.model.types.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private ClientDTO client;

    private ProjectStatus status;

    private List<ActivityProjectDTO> activities = new ArrayList<>();

}
