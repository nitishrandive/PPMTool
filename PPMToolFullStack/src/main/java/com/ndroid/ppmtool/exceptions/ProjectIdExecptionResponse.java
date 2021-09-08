package com.ndroid.ppmtool.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectIdExecptionResponse {
    private String projectIdentifier;

    public ProjectIdExecptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    /*public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }*/
}
