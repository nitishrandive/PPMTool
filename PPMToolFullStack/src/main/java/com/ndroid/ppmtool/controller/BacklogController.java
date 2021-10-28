package com.ndroid.ppmtool.controller;

import com.ndroid.ppmtool.domain.Project;
import com.ndroid.ppmtool.domain.ProjectTask;
import com.ndroid.ppmtool.services.MapValidationErrorService;
import com.ndroid.ppmtool.services.ProjectService;
import com.ndroid.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/backlog")
public class BacklogController {

    @Autowired
    MapValidationErrorService mapValidationErrorService;

    @Autowired
    ProjectTaskService projectTaskService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> createProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String backlog_id){
        projectTaskService.addProjectTask(backlog_id,projectTask);

        ResponseEntity<?> errorResponse = mapValidationErrorService.getMapValidationError(result);
        if(errorResponse.getBody()!=null)
            return errorResponse;
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.CREATED);
    }

    @GetMapping("{backlog_id}")
    public Iterable<ProjectTask>getProjectTask (@PathVariable String backlog_id )
    {
        return projectTaskService.findProjectTaskById(backlog_id);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?>getProjectTaskBySequence(@PathVariable String backlog_id,@PathVariable String pt_id){
        return new ResponseEntity(projectTaskService.findProjectTaskBySequence(backlog_id.toUpperCase(),pt_id),HttpStatus.OK);
    }

    @PatchMapping("{backlog_id}/{pt_id}")
    public ResponseEntity<?>updateProjectBySequence(@Valid @RequestBody ProjectTask projectTask,BindingResult result,@PathVariable String backlog_id,@PathVariable String pt_id){
        ResponseEntity<?> errorResponse  = mapValidationErrorService.getMapValidationError(result);
        if (errorResponse.getBody()!=null)
            return  errorResponse;
        ProjectTask updatedTask = projectTaskService.updateProjectTaskBySequence(projectTask,backlog_id,pt_id);
        ResponseEntity<?>  successfulResponse  = new ResponseEntity(updatedTask,HttpStatus.OK);
        return successfulResponse;

    }
    @DeleteMapping("{backlog_id}/{pt_id}")
    public void deleteProjectTaskBySequence(@PathVariable String backlog_id,@PathVariable String pt_id){
        projectTaskService.deleteProjectTaskBySequence(backlog_id,pt_id);
    }
}
