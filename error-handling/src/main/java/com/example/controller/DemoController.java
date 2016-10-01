package com.example.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.dto.DemoDto;
import com.example.exception.ApplicationException;
import com.example.exception.DemoException;
import com.example.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @Autowired
    private DemoService demoService;

    @GetMapping(path = "/{id}")
    public DemoDto demo(@PathVariable int id) {
        if (id == 0) {
            throw new DemoException();
        }
        if (id == 100) {
            throw new NullPointerException();
        }

        return demoService.getDemoDto1();
    }

    @ExceptionHandler(ApplicationException.class)
    public String error(HttpServletResponse response, Exception ex) {
        setHttpStatus(response, ex);
        return "demo error";
    }

}
