package com.ndroid.ppmtool.services;

import com.ndroid.ppmtool.domain.Backlog;
import com.ndroid.ppmtool.domain.Project;
import com.ndroid.ppmtool.domain.ProjectTask;
import com.ndroid.ppmtool.exceptions.ProjectNotFoundException;
import com.ndroid.ppmtool.repositories.BacklogRepository;
import com.ndroid.ppmtool.repositories.ProjectRepository;
import com.ndroid.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    ProjectTaskRepository projectTaskRepository;

    @Autowired
    BacklogRepository backlogRepository;

    @Autowired
    ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask){
        // get the backlog for a given projectIdentifier
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(backlog ==null){
            throw new ProjectNotFoundException("project Not Found");
        }

        // setup the backlog to ProjectTask
        projectTask.setBacklog(backlog);
        //Set the the project task id (projectIdentifier + PT Sequence)
        Integer backLogSequence = backlog.getPTSequence();
        backLogSequence++;
        backlog.setPTSequence(backLogSequence );
        projectTask.setProjectSequence(projectIdentifier.toUpperCase()+"-"+backLogSequence);
        // set the priority
        if (projectTask.getPriority()==null || projectTask.getPriority()==0)
             projectTask.setPriority(3);
        //set the status
        if(projectTask.getStatus()==null||projectTask.getStatus()=="")
            projectTask.setStatus("TO_DO");
        //save the Project Task
        projectTask.setProjectIdentifier(projectIdentifier.toUpperCase());
        projectTaskRepository.save(projectTask);
        return  projectTask;
    }

    public ProjectTask findProjectTaskBySequence(String backlog_id,String pt_id){
        Project project = projectRepository.findProjectByProjectIdentifier(backlog_id);
        if(project==null)
        {
            throw new ProjectNotFoundException("Project Id - "+"'"+backlog_id+"'"+" does not exist");
        }
        ProjectTask projectTask = projectTaskRepository.findProjectTaskByProjectSequence(pt_id);
        if(projectTask==null)
        {
            throw new ProjectNotFoundException("Project Task - " +pt_id+" does not exist" );
        }
        if(!projectTask.getProjectIdentifier().equals(backlog_id))
        {
            throw new ProjectNotFoundException("Project Task - " +pt_id+" does not exist in Project Task" );
        }
        return projectTask;
    }

    public ProjectTask updateProjectTaskBySequence(ProjectTask updateTask,String backlog_id,String pt_id)
    {
        //Project project = projectRepository.findProjectByProjectIdentifier(backlog_id);
        ProjectTask projectTask = findProjectTaskBySequence(backlog_id,pt_id);

        ProjectTask updatedTask = projectTaskRepository.save(updateTask);
        return  updatedTask;
    }

    public Iterable<ProjectTask>findProjectTaskById(String backlog_id)
    {
        return projectTaskRepository.findProjectTaskByProjectIdentifierOrderByPriority(backlog_id);
    }

    public void deleteProjectTaskBySequence(String backlog_id,String pt_id){
        ProjectTask projectTask = findProjectTaskBySequence(backlog_id,pt_id);
        projectTaskRepository.delete(projectTask);
    }
}
