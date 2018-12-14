package core.excetionHandler;

import core.exception.SpliterNotFoundEx;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//只有实现handlerMethod @Controller支持    extends AbstractController不支持
@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(SpliterNotFoundEx.class)
    public String SpliterNotFoundHandler(){
        return "/WEB-INF/index";
    }
}
