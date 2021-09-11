package com.ndroid.ppmtool.services;

import com.ndroid.ppmtool.domain.Project;
import com.ndroid.ppmtool.exceptions.ProjectIdException;
import com.ndroid.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project createOrUpdateProject(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project Id " + project.getProjectName() + " Already Exist");
        }
    }

    public Project findProjectByIdentifier(String id) {
           Project project = projectRepository.findProjectByProjectIdentifier(id.toUpperCase());
           if (project == null)
               throw new ProjectIdException("Project Id " + id + "Not Found");
       return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId)
    {
        Project project = projectRepository.findProjectByProjectIdentifier(projectId.toUpperCase());
        if(project == null)
        {
            throw new ProjectIdException("Project Id "+projectId+" does not exists.");
        }

        projectRepository.deleteByProjectIdentifier(projectId.toUpperCase());
        //projectRepository.delete(project);
    }

    public Project updateProjectDetails(Project project){
        Project projectDetails = projectRepository.findProjectByProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        if(projectDetails == null)
            throw new ProjectIdException("Project Id "+ project.getProjectIdentifier()+" does not exist");
        projectDetails.setProjectName(project.getProjectName());
        projectDetails.setDescription(project.getDescription());
        projectRepository.save(projectDetails);
        return projectDetails;
    }

}
