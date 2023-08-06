package com.agnes.pm.service;

import com.agnes.pm.dto.ActivityDTO;
import com.agnes.pm.dto.ProjectDTO;
import com.agnes.pm.model.Project;
import com.agnes.pm.model.types.ActivityStatus;
import com.agnes.pm.model.types.ProjectStatus;
import com.agnes.pm.repository.ProjectRepository;
import com.agnes.pm.translate.ProjectTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ActivityService activityService;

    public Optional<Project> create(@NotNull Project project) {
        clientService.findById(project.getClient().getId());
        return Optional.of(repository.save(project));
    }

    public Optional<Project> update(@NotNull Project project) {
        repository.findById(project.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "project.not.found"));
        return Optional.of(repository.save(project));
    }

    public ProjectDTO findById(@NotNull Long id) {
        return repository.findById(id)
                .map(ProjectTranslator::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "project.not.found"));
    }

    public void delete(@NotNull Long id) {
        ProjectDTO project = this.findById(id);
        if (project.getStatus().equals(ProjectStatus.DOING))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "project.cannot.be.deleted.doing.status");

        if (this.checkActivitiesDoing(project))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "project.cannot.be.deleted.activity.doing.status");

        repository.deleteById(id);
    }

    public List<ProjectDTO> list() {
        return repository.findAll().stream()
                .map(ProjectTranslator::toDto)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> listByStatus(ProjectStatus status) {
        return repository.findByStatus(status).stream()
                .map(ProjectTranslator::toDto)
                .collect(Collectors.toList());
    }

    private boolean checkActivitiesDoing(ProjectDTO project) {
        List<ActivityDTO> activityDTOList = activityService.findByProject(ProjectTranslator.toModel(project));
        for (ActivityDTO activity : activityDTOList) {
            if (activity.getStatus().equals(ActivityStatus.DOING))
                return true;
        }
        return false;
    }

}