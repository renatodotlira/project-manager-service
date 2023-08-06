package com.agnes.pm.resource;

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
public class ClientResource {

    @JsonProperty(value="name", required = true)
    @NotNull(message="client.mane.required")
    private String name;

}
