package com.ndroid.ppmtool.repositories;

import com.ndroid.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {
    Backlog findByProjectIdentifier(String projectIdentifier);
}
