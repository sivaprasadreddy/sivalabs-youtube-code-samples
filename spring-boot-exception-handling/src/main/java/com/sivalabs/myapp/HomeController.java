package com.sivalabs.myapp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HomeController
{
    @GetMapping()
    public String demo(@RequestParam(required = false) String code, Model model) {
        if("400".equals(code)) {
            throw new BadRequestException("Bad code");
        } else if("403".equals(code)) {
            throw new AccessDeniedException("You are not authorized to access this page");
        } else if("500".equals(code)) {
            throw new RuntimeException("Something terrible happened!!! Run.......");
        }
        model.addAttribute("greeting", "Hello Siva");
        return "demo";
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

@ResponseStatus(HttpStatus.FORBIDDEN)
class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}