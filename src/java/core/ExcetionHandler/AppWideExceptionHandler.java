package core.ExcetionHandler;

import core.Exception.SpliterNotFoundEx;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(SpliterNotFoundEx.class)
    public String SpliterNotFoundHandler(){
        return "/WEB-INF/index";
    }
}
