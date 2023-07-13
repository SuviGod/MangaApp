package ua.sulima.mangaapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.FileNotFoundException;


@Slf4j
public class MvcExceptionHandler {

//    @ExceptionHandler(value
//            = { IllegalArgumentException.class, IllegalStateException.class })
//    @ResponseStatus(value= HttpStatus.NOT_IMPLEMENTED, reason="IOException occured")
//    protected ModelAndView handleConflict(
//            RuntimeException ex) {
//        String bodyOfResponse = "This should be application specific";
//        log.error("handling error");
//        ModelAndView mav = new ModelAndView();
//        mav.setStatus(HttpStatusCode.valueOf(502));
//        mav.addObject("exception", ex);
//        mav.addObject("url", "llllev loh");
//        mav.setViewName("error");
//        return mav;
//    }

//    @ExceptionHandler(NoHandlerFoundException.class)
    @ExceptionHandler(value = {HttpClientErrorException.class, FileNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Exception exception, Model model) {


        model.addAttribute("error_code",
                HttpStatus.NOT_FOUND.value());
        return "error";
    }

//    @ExceptionHandler(value = IOException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleNotFound(IOException exception, Model model) {
//        exception.printStackTrace();
//        log.error(exception.getMessage());
//        model.addAttribute("error_code",
//                HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return "error";
//    }
}
