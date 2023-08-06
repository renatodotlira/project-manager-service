package com.agnes.pm.repository;

import com.agnes.pm.model.Activity;
import com.agnes.pm.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByProject(Project project);

}
