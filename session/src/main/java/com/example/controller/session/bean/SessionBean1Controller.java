package com.example.controller.session.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
@RequestMapping("/sessionbean1")
public class SessionBean1Controller {

    @Autowired
    private SessionBean sessionBean;

    @ModelAttribute("form")
    public Form setUpForm() {
        return new Form("non", "non");
    }

    @GetMapping(path = "input")
    public String input(SessionStatus sessionStatus, Model model) {
        sessionStatus.setComplete();
        return "/session/bean/input";
    }

    @PostMapping(path = "post")
    public String postInput(Form form) {
        sessionBean.setUser(form.getUser());
        sessionBean.setPhoneNumber(form.getPhoneNumber());
        sessionBean.setAddress(form.getUser() + form.getPhoneNumber() + "@demo.com");
        return "redirect:/sessionbean2/confirm";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Form implements Serializable {

        private String user;

        private String phoneNumber;

    }

}
