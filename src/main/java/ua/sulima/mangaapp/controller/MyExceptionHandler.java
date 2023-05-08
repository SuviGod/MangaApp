package ua.sulima.mangaapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
//    @ResponseStatus(value= HttpStatus.NOT_IMPLEMENTED, reason="IOException occured")
    protected ModelAndView handleConflict(
            RuntimeException ex) {
        String bodyOfResponse = "This should be application specific";
        log.error("handling error");
        ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatusCode.valueOf(502));
        mav.addObject("exception", ex);
        mav.addObject("url", "llllev loh");
        mav.setViewName("error");
        return mav;
    }

//    @ExceptionHandler(NoHandlerFoundException.class)
    @ExceptionHandler(value = HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(HttpClientErrorException exception, Model model) {

        model.addAttribute("error_code",
                HttpStatus.NOT_FOUND.value());
        return "error";
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleNotFound(IOException exception, Model model) {
        model.addAttribute("error_code",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error";
    }
}
