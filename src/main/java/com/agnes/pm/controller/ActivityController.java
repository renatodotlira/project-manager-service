package com.agnes.pm.controller;

import com.agnes.pm.dto.ActivityDTO;
import com.agnes.pm.resource.ActivityResource;
import com.agnes.pm.service.ActivityService;
import com.agnes.pm.translate.ActivityTranslator;
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
@RequestMapping(value = "/v1/activities")
@Api(tags = "Activities")
public class ActivityController {

    @Autowired
    private ActivityService service;

    @GetMapping
    @ApiOperation(value = "List activities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Activity listed successfully.", response = ActivityDTO.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity<List<ActivityDTO>> list() {
        List<ActivityDTO> activityList = service.list();
        return ResponseEntity.ok(activityList);
    }

    @PostMapping
    @ApiOperation(value = "Create activity")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Activity created successfully.", response = ActivityResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity create(@RequestBody @Validated ActivityResource activityResource) {

        return service
                .create(ActivityTranslator.toModel(activityResource))
                .map(activity -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Update Activity")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Activity updated successfull.", response = ActivityResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Validated ActivityResource activityResource) {

        return service
                .update(ActivityTranslator.toEntity(activityResource, id))
                .map(activity -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find activity by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Return activity from informed id.", response = ActivityResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 404, message = "Activity doesn't exist."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete activity")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Activity deleted successfully.", response = ActivityResource.class),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 404, message = "Activity doesn't exist."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
