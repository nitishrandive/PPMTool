package com.ndroid.ppmtool.controller;

import com.ndroid.ppmtool.domain.Project;
import com.ndroid.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
        if(result.hasErrors())
        {
            System.out.println(result.hasErrors());
            return new ResponseEntity<String>("Invalid Request",HttpStatus.BAD_REQUEST);
        }

        Project projectInfo = projectService.createOrUpdateProject(project);
        return new ResponseEntity<Project>(projectInfo, HttpStatus.CREATED);
    }

}
