package com.yfyld.tracker.component;


import com.yfyld.tracker.util.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
@Order(2)
public class BindingResultAspect {
    @Pointcut("execution(public * com.yfyld.tracker.controller.*.*(..))")
    public void BindingResult(){

    }

    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args=joinPoint.getArgs();
        for(Object arg:args){
            if(arg instanceof BindingResult){
                BindingResult result =(BindingResult) arg;
                if(result.hasErrors()){
                    return new CommonResult().validateFailed("11111");
                }
            }
        }
        return joinPoint.proceed();
    }
}
