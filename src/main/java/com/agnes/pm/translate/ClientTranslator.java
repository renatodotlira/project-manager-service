package com.agnes.pm.translate;

import com.agnes.pm.dto.ClientDTO;
import com.agnes.pm.model.Client;
import com.agnes.pm.resource.ClientResource;

import javax.validation.constraints.NotNull;

public class ClientTranslator {

    public static Client toModel(@NotNull final ClientResource from){

        return Client.builder()
                .name(from.getName())
                .build();
    }

    public static Client toModel(@NotNull final ClientDTO from){

        return Client.builder()
                .id(from.getId())
                .name(from.getName())
                .build();
    }

    public static Client toEntity(@NotNull final ClientResource from, @NotNull Long id){

        return Client.builder()
                .id(id)
                .name(from.getName())
                .build();
    }

    public static ClientDTO toDto(@NotNull final Client from){

        return ClientDTO.builder()
                .id(from.getId())
                .name(from.getName())
                .build();
    }

}
