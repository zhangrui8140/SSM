package springsource.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class BaseAspect {

    private static final Logger logger=LoggerFactory.getLogger(BaseAspect.class);

    public BaseAspect(){
        System.out.print("");
    }


    @Pointcut(value = "execution(* business.service..*(..))")
    public void controllerBeforePointcut(){}

    @Around(value ="controllerBeforePointcut()",argNames = "joinPoint")
    public void controllerBeforeAdvice(ProceedingJoinPoint joinPoint){
        try {
            joinPoint.proceed();
        }
        catch (Throwable e){

        }
    }
}
