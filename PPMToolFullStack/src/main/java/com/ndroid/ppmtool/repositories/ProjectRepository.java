package com.ndroid.ppmtool.repositories;

import com.ndroid.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findProjectByProjectIdentifier(String projectId);
}
