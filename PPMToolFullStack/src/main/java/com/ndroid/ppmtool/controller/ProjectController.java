package com.ndroid.ppmtool.controller;

import com.ndroid.ppmtool.domain.Project;
import com.ndroid.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
        if(result.hasErrors())
        {
            List<FieldError> errorMessages = result.getFieldErrors();
            Map<String,String> errorMap = new HashMap<String,String>();
            for (FieldError error : errorMessages)
            {
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
        }

        Project projectInfo = projectService.createOrUpdateProject(project);
        return new ResponseEntity<Project>(projectInfo, HttpStatus.CREATED);
    }

}
