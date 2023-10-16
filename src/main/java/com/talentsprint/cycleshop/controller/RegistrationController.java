package com.talentsprint.cycleshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.talentsprint.cycleshop.service.DomainUserService;
import com.talentsprint.cycleshop.service.RegistrationForm;

@RestController
@RequestMapping("/api/cycle")

public class RegistrationController {

    @Autowired

    private DomainUserService domainUserService;

    @GetMapping("/register")

    public String getRegistrationForm(Model model) {

        if (!model.containsAttribute("registrationForm")) {
            model.addAttribute("registrationForm", new RegistrationForm());
        }
        return "register";
    }

    @PostMapping("/register")

    public String register(@ModelAttribute("registrationForm") RegistrationForm registrationForm,

            BindingResult bindingResult,

            RedirectAttributes attr) {

        System.out.println("id:" + registrationForm.getName());

        System.out.println("id:" + registrationForm.getPassword());

        System.out.println("id:" + registrationForm.getRepeatPassword());

        System.out.println("id:" + registrationForm.getRole());

        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.registrationForm", bindingResult);
            attr.addFlashAttribute("registrationForm", registrationForm);
            return "redirect:/register";
        }

        if (!registrationForm.isValid()) {

            attr.addFlashAttribute("message", "Passwords must match");

            attr.addFlashAttribute("registrationForm", registrationForm);

            return "redirect:/register";

        }

        System.out.println(domainUserService.save(registrationForm.getName(), registrationForm.getPassword(),
                registrationForm.getRole()));

        attr.addFlashAttribute("result", "Registration success!");

        return "redirect:/login";

    }

}