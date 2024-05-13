package org.nikitakosh.estateagency.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.nikitakosh.estateagency.models.User;
import org.nikitakosh.estateagency.service.ApartmentService;
import org.nikitakosh.estateagency.service.auth.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/apartments")
public class ApartmentController {
    private final ApartmentService apartmentService;
    private final AuthService authService;

    @GetMapping("/{id}")
    public String findOne(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("apartment", apartmentService.findOne(id));
        return "oneApartment";
    }

    @GetMapping("/{id}/buy")
    public String buyApartment(@PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((User) authentication.getPrincipal());
        apartmentService.buy(id, user.getUsername());
        return "redirect:/api/v1/projects";
    }

    @GetMapping("/account")
    public String account(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = ((User) authentication.getPrincipal());
        model.addAttribute("user", authService.loadUserByUsername(user.getUsername()));
        return "userApartments";
    }
}
