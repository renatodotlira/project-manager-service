package com.agnes.pm.controller;

import com.agnes.pm.dto.ClientDTO;
import com.agnes.pm.resource.ClientResource;
import com.agnes.pm.service.ClientService;
import com.agnes.pm.translate.ClientTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/clients")
@Api(tags = "Clients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    @ApiOperation(value = "List clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clients listed successfully.", response = ClientDTO.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity<List<ClientDTO>> list() {

        List<ClientDTO> clientList =
                service
                        .list()
                        .stream()
                        .map(ClientTranslator::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(clientList);
    }

    @PostMapping
    @ApiOperation(value = "Create client")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Client created successfully.", response = ClientResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity create(@RequestBody @Validated ClientResource clientResource) {

        return service
                .create(ClientTranslator.toModel(clientResource))
                .map(client -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Update Client")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Client updated successfull.", response = ClientResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Validated ClientResource clientResource) {

        return service
                .update(ClientTranslator.toEntity(clientResource, id))
                .map(client -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find client by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Return client from informed id.", response = ClientResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 404, message = "Client doesn't exist."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(service
                .findById(id));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete client")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Client deleted successfully.", response = ClientResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 404, message = "Client doesn't exist."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

}
