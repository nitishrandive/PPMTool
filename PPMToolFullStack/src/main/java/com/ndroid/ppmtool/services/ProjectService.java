package com.ndroid.ppmtool.services;

import com.ndroid.ppmtool.domain.Project;
import com.ndroid.ppmtool.exceptions.ProjectIdException;
import com.ndroid.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project createOrUpdateProject(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch(Exception e)
        {
            throw new ProjectIdException("Project Id "+ project.getProjectName()+" Already Exist");
        }

    }
}
