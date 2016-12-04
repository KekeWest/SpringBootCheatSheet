package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exception.ApplicationException;
import com.example.exception.InternalServerErrorException;
import com.example.exception.NotFoundException;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping(path = "/{status}")
    public String demo(@PathVariable String status) {
        if ("500".equals(status)) {
            throw new InternalServerErrorException();
        }
        if ("404".equals(status)) {
            throw new NotFoundException();
        }
        if ("null".equals(status)) {
            throw new NullPointerException();
        }
        if ("appex".equals(status)) {
            throw new ApplicationException();
        }

        return "demo";
    }

    @ExceptionHandler(ApplicationException.class)
    public String controllerError(HttpServletRequest request, HttpServletResponse responce) {
        return "app_error";
    }

}
