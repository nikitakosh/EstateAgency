package org.nikitakosh.estateagency.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nikitakosh.estateagency.controller.dto.SignInDto;
import org.nikitakosh.estateagency.controller.dto.SignUpDto;
import org.nikitakosh.estateagency.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;



    @GetMapping("/signUp")
    public String signUp(Model model, @ModelAttribute("data") SignUpDto signUpDto) {
        model.addAttribute("data", signUpDto);
        return "signUp";
    }

    @GetMapping("/signIn")
    public String signIn(Model model, @ModelAttribute("data") SignInDto signInDto) {
        model.addAttribute("data", signInDto);
        return "signIn";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("data") SignUpDto data) {
        log.info("in signUp");
        service.signUp(data);
        return "redirect:/api/v1/projects";
    }


}