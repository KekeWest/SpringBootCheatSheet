package com.example.controller.session.attribute;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/sessionattributes")
@SessionAttributes(
        types = {SessionAttributesController.Form.class},
        names = {"address"}
        )
public class SessionAttributesController {

    @ModelAttribute("form")
    public Form setUpForm() {
        return new Form("non", "non");
    }

    @GetMapping(path = "input")
    public String input(SessionStatus sessionStatus, Model model) {
        sessionStatus.setComplete();
        return "/session/attributes/input";
    }

    @PostMapping(path = "post")
    public String postInput(Model model, Form form) {
        log.info("form={}", form);
        model.addAttribute("address", form.getUser() + form.getPhoneNumber() + "@demo.com");
        return "redirect:confirm";
    }

    @GetMapping(path = "confirm")
    public String confirm() {
        return "/session/attributes/confirm";
    }

    @GetMapping(path = "complete")
    public String complete(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "/session/attributes/complete";
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Form implements Serializable {

        private String user;

        private String phoneNumber;

    }

}
