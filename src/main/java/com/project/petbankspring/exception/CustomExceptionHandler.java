package com.project.petbankspring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

    // 404
    @ExceptionHandler(EntityNotFoundException.class)
    public final ModelAndView handleEntityNotFound(EntityNotFoundException ex) {
        ModelAndView view = new ModelAndView("404");
        LOG.info("EntityNotFoundException" + ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDetails(details);

        view.addObject("exception", errorResponse);

        return view;
    }

    // All 404
    @ExceptionHandler(RuntimeException.class)
    public final String noHandlerFound(RuntimeException ex) {
        ModelAndView view = new ModelAndView("404");
        LOG.info("RuntimeException" + ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDetails(details);

        view.addObject("exception", errorResponse);

        return "404";
    }

    // 403 Access Denied
    @ExceptionHandler(AccessDeniedException.class)
    public final ModelAndView handleAuthorization(AccessDeniedException ex) {
        ModelAndView view = new ModelAndView("403");
        LOG.info("AccessDeniedException" + ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDetails(details);

        view.addObject("exception", errorResponse);

        return view;
    }

    //All Error exception
    @ExceptionHandler(Exception.class)//Throwable.class
    public final ModelAndView handleException(Exception ex) {
        ModelAndView view = new ModelAndView("500");
        LOG.info("Exception" + ex);
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDetails(details);

        view.addObject("exception", errorResponse);

        return view;
    }

}
