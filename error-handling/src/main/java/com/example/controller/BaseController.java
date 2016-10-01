package com.example.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

    protected void setHttpStatus(HttpServletResponse response, Exception ex) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            response.setStatus(responseStatus.code().value());
        }
    }

}
