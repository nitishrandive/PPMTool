package com.ndroid.ppmtool.controller;

import com.ndroid.ppmtool.domain.Project;
import com.ndroid.ppmtool.exceptions.ProjectIdException;
import com.ndroid.ppmtool.services.MapValidationErrorService;
import com.ndroid.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    MapValidationErrorService mapValidationErorrService;

    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
        //get all the errors in ResponseEntity of type Map if any errors are present
        ResponseEntity<Map<String, String>> errorMap = mapValidationErorrService.getMapValidationError(result);
        if (errorMap.getBody() != null) return errorMap;
        Project projectInfo = projectService.createOrUpdateProject(project);
        return new ResponseEntity<Project>(projectInfo, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> findProjectByIdentifier(@PathVariable String projectId) {
            Project project = projectService.findProjectByIdentifier(projectId);
                return new ResponseEntity<Project>(project, HttpStatus.OK);

    }

}
