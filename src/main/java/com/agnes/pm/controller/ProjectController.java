package com.agnes.pm.controller;

import com.agnes.pm.dto.ProjectDTO;
import com.agnes.pm.model.types.ProjectStatus;
import com.agnes.pm.resource.ProjectResource;
import com.agnes.pm.service.ProjectService;
import com.agnes.pm.translate.ProjectTranslator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/projects")
@Api(tags = "Projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    @ApiOperation(value = "List projects")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Projects listed successfully.", response = ProjectDTO.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity<List<ProjectDTO>> list() {
        List<ProjectDTO> projectList = service.list();
        return ResponseEntity.ok(projectList);
    }

    @GetMapping("/status/{status}")
    @ApiOperation(value = "List projects by status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Projects listed successfully.", response = ProjectDTO.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity<List<ProjectDTO>> listByStatus(@PathVariable ProjectStatus status) {
        List<ProjectDTO> projectList = service.listByStatus(status);
        return ResponseEntity.ok(projectList);
    }

    @PostMapping
    @ApiOperation(value = "Create project")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Project created successfully.", response = ProjectResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity create(@RequestBody @Validated ProjectResource projectResource) {
        return service
                .create(ProjectTranslator.toModel(projectResource))
                .map(project -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Update Project")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Project updated successfully.", response = ProjectResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Validated ProjectResource projectResource) {
        return service
                .update(ProjectTranslator.toEntity(projectResource, id))
                .map(project -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find project by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Return project from informed id.", response = ProjectResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 404, message = "Project doesn't exist."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(service
                .findById(id));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete project")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Project deleted successfully.", response = ProjectResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 404, message = "Project doesn't exist."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

}
