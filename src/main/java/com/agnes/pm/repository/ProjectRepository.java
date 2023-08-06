package com.agnes.pm.repository;

import com.agnes.pm.model.Project;
import com.agnes.pm.model.types.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByStatus(ProjectStatus status);
}
