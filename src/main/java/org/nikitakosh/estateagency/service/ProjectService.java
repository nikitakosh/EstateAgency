package org.nikitakosh.estateagency.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.nikitakosh.estateagency.models.Project;
import org.nikitakosh.estateagency.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void save(Project project) {
        projectRepository.save(project);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findOne(Integer id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("ЖК с таким id не существует")
        );
    }
}
