package com.agnes.pm.service;

import com.agnes.pm.dto.ActivityDTO;
import com.agnes.pm.model.Activity;
import com.agnes.pm.model.Project;
import com.agnes.pm.repository.ActivityRepository;
import com.agnes.pm.translate.ActivityTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private ProjectService projectService;

    public Optional<Activity> create(@NotNull Activity activity) {
        projectService.findById(activity.getProject().getId());
        return Optional.of(repository.save(activity));
    }

    public Optional<Activity> update(@NotNull Activity activity) {
        repository.findById(activity.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "activity.not.found"));
        return Optional.of(repository.save(activity));
    }

    public ActivityDTO findById(@NotNull Long id) {
        return repository.findById(id)
                .map(ActivityTranslator::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "activity.not.found"));
    }

    public List<ActivityDTO> findByProject(@NotNull Project project) {
        return repository.findByProject(project)
                .stream().map(ActivityTranslator::toDto)
                .collect(Collectors.toList());
    }

    public void delete(@NotNull Long id) {
        this.findById(id);
        repository.deleteById(id);
    }

    public List<ActivityDTO> list() {
        return repository.findAll().stream()
                .map(ActivityTranslator::toDto)
                .collect(Collectors.toList());
    }

}