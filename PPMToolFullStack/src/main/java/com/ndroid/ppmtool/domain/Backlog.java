package com.ndroid.ppmtool.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    @Column(updatable = false)
    private String projectIdentifier;
    @OneToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "project_id",nullable = false)
    @JoinColumn(name = "project_id",nullable = false)
    @JsonIgnore
    private Project project;
    public Backlog() {
    }
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH,mappedBy = "backlog",orphanRemoval = true)
    private List<ProjectTask> projectasks = new ArrayList<>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public List<ProjectTask> getProjectasks() {
        return projectasks;
    }

    public void setProjectasks(List<ProjectTask> projectasks) {
        this.projectasks = projectasks;
    }
}
