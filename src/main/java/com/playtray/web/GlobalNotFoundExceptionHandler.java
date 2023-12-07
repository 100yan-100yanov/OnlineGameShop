package com.playtray.web;

import com.playtray.error.AccessForbiddenException;
import com.playtray.error.ObjectNotFoundException;
import com.playtray.error.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalNotFoundExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(ObjectNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("errors/404");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView handleUserUnauthorized(UnauthorizedException exception) {
        ModelAndView modelAndView = new ModelAndView("errors/401");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView handleAccessForbidden(AccessForbiddenException exception) {
        ModelAndView modelAndView = new ModelAndView("errors/403");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }
}
