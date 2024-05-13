package org.nikitakosh.estateagency.controller;

import lombok.RequiredArgsConstructor;
import org.nikitakosh.estateagency.models.Project;
import org.nikitakosh.estateagency.models.User;
import org.nikitakosh.estateagency.models.enums.UserRole;
import org.nikitakosh.estateagency.service.ProjectService;
import org.nikitakosh.estateagency.service.auth.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public String findAll(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("isAdmin", ((User) authentication.getPrincipal()).getRole() == UserRole.ADMIN);
        return "allProjects";
    }

    @GetMapping("/{id}")
    public String findOne(Model model, @PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("project", projectService.findOne(id));
        model.addAttribute("isAdmin", ((User) authentication.getPrincipal()).getRole() == UserRole.ADMIN);
        return "oneProject";
    }

}
