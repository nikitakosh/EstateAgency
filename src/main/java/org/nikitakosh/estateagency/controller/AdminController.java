package org.nikitakosh.estateagency.controller;

import lombok.RequiredArgsConstructor;
import org.nikitakosh.estateagency.models.Apartment;
import org.nikitakosh.estateagency.models.Project;
import org.nikitakosh.estateagency.service.ApartmentService;
import org.nikitakosh.estateagency.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AdminController {

    private final ApartmentService apartmentService;
    private final ProjectService projectService;

    @PostMapping("/project")
    public String addProject(@ModelAttribute("project") Project project) {
        projectService.save(project);
        return "redirect:/api/v1/projects";
    }

    @GetMapping("/project")
    public String addProject(Model model) {
        model.addAttribute("project", new Project());
        return "createProject";
    }

    @GetMapping("/apartment")
    public String addApartment(Model model, @RequestParam("projectId") Integer projectId) {
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("projectId", projectId);
        return "createApartment";
    }

    @PostMapping("/apartment")
    public String addApartment(@RequestParam("projectId") Integer projectId, @ModelAttribute("apartment") Apartment apartment) {
        apartmentService.save(projectId, apartment);
        return "redirect:/api/v1/projects";
    }


}
