package com.agnes.pm.dto;

import com.agnes.pm.model.types.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

    private Long id;

    private String title;

    private String description;

    private ProjectActivityDTO project;

    private ActivityStatus status;

}
