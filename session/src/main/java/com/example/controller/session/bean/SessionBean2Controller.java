package com.example.controller.session.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/sessionbean2")
@SessionAttributes("scopedTarget.sessionBean")
public class SessionBean2Controller {

    @Autowired
    private SessionBean sessionBean;

    @GetMapping(path = "confirm")
    public String confirm(Model model) {
        return "/session/bean/confirm";
    }

    @GetMapping(path = "complete")
    public String complete(SessionStatus sessionStatus) {
        return "/session/bean/complete";
    }

}