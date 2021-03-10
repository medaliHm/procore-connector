package com.procore.connector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.procore.connector.models.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

}
