package com.ndroid.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String projectIdentifier;
    private String projectSequence;
    @NotBlank(message = "Summary Can not be Blank")
    private String Summary;
    private String acceptanceCriteria;
    private Integer priority;
    private Date dueDate;
    private Date created_at;
    private Date updated_at;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "backlog_id",nullable = false,updatable = false)
    @JsonIgnore
    private Backlog backlog;
    public ProjectTask() {
    }

    @PrePersist
    protected void onCreate(){
        created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        updated_at = new Date();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectSequence() {
        return projectSequence;
    }

    public void setProjectSequence(String projectSequence) {
        this.projectSequence = projectSequence;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "Id=" + Id +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", projectSequence='" + projectSequence + '\'' +
                ", Summary='" + Summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
