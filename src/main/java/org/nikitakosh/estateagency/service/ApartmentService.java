package org.nikitakosh.estateagency.service;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.nikitakosh.estateagency.models.Apartment;
import org.nikitakosh.estateagency.models.Project;
import org.nikitakosh.estateagency.models.User;
import static org.nikitakosh.estateagency.models.enums.ApartmentStatus.RESERVED;
import org.nikitakosh.estateagency.repository.ApartmentRepository;
import org.nikitakosh.estateagency.repository.ProjectRepository;
import org.nikitakosh.estateagency.service.auth.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ProjectService projectService;
    private final AuthService authService;

    public Apartment findOne(Integer id) {
        return apartmentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Apartment с таким id не существует")
        );
    }

    public void save(Integer projectId, Apartment apartment) {
        Project project = projectService.findOne(projectId);
        project.getApartments().add(apartment);
        apartment.setProject(project);
        apartmentRepository.save(apartment);
        projectService.save(project);
    }

    @Transactional
    public void buy(Integer apartmentId, String username) {
        Apartment apartment = findOne(apartmentId);
        User user = (User) authService.loadUserByUsername(username);
        apartment.setUser(user);
        apartment.setStatus(RESERVED);
        user.getApartments().add(apartment);
    }
}
