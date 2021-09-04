package com.ndroid.ppmtool.services;

import com.ndroid.ppmtool.domain.Project;
import com.ndroid.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project createOrUpdateProject(Project project){
        // any Business Logic here
        return projectRepository.save(project);
    }
}
