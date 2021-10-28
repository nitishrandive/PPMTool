package com.ndroid.ppmtool.repositories;

import com.ndroid.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
    Iterable<ProjectTask> findProjectTaskByProjectIdentifierOrderByPriority(String projectIdentifier);
    ProjectTask findProjectTaskByProjectIdentifierAndProjectSequence(String backlog_id,String pt_id);
    ProjectTask findProjectTaskByProjectSequence(String sequence);
}
