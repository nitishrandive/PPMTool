package com.ndroid.ppmtool.repositories;

import com.ndroid.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findProjectByProjectIdentifier(String projectId);
    //Project deleteByProjectIdentifier(String projectIdentifier);
    void deleteByProjectIdentifier(String projectId);
}
