package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController implements ErrorController {

    public static final String ERROR_EXCEPTION_KEY = CustomErrorController.class.getName() + "."
            + Throwable.class.getName();

    @Value("${server.error.path:${error.path:/error}}")
    private String errorPath;

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(produces = "text/html")
    public String errorHtml(HttpServletRequest request, HttpServletResponse response) {
        Throwable error = errorAttributes.getError(new ServletRequestAttributes(request));
        try {
            int statusCode = response.getStatus();
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/not_found_error";
            }
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/system_error";
            }
        } catch (Exception e) {
        }

        return "error/error";
    }

    @RequestMapping
    @ResponseBody
    public String errorAPI(HttpServletRequest request, HttpServletResponse response) {
        Throwable error = errorAttributes.getError(new ServletRequestAttributes(request));
        return "error";
    }

}
