package org.nikitakosh.estateagency.repository;

import org.nikitakosh.estateagency.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
