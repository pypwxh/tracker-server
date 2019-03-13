package com.yfyld.tracker.component;


import com.yfyld.tracker.bo.WebLog;
import com.yfyld.tracker.util.JsonUtil;
import com.yfyld.tracker.util.RequestUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Order(1)
public class WebLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);
    private ThreadLocal<Long> startTime=new ThreadLocal<>();

    @Pointcut("execution(public * com.yfyld.tracker.core.controller.*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(value="webLog()",returning="ret")
    public void doAfterReturning(Object ret) throws Throwable{

    }

    @Around("webLog()")
    public Object doArount(ProceedingJoinPoint joinPoint) throws  Throwable{
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        WebLog webLog=new WebLog();
        Object result = joinPoint.proceed();
        Signature signature=joinPoint.getSignature();
        MethodSignature methodSignature=(MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if(method.isAnnotationPresent(ApiOperation.class)){
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(log.value());
        }
        Long endTime = System.currentTimeMillis();
        webLog.setBasePath(RequestUtil.getBasePath(request));
        webLog.setIp(request.getRemoteUser());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(getParameter(method,joinPoint.getArgs()));
        webLog.setResult(result);
        webLog.setSpendTime((int)(endTime-startTime.get()));
        webLog.setStartTime(startTime.get());
        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        LOGGER.info("{}", JsonUtil.objectToJson(webLog));
        return result;


    }

    private Object getParameter(Method method,Object[] args){
        List<Object> argList =new ArrayList<>();
        Parameter[] parameters=method.getParameters();
        for(int i=0;i<parameters.length;i++){
            RequestBody requestBody=parameters[i].getAnnotation(RequestBody.class);
            if(requestBody !=null){
                argList.add(args[i]);
            }
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if(requestParam!=null){
                Map<String,Object> map=new HashMap<>();
                String key=parameters[i].getName();
                if(!StringUtils.isEmpty(requestParam.value())){
                    key=requestParam.value();
                }
                map.put(key,args[i]);
                argList.add(map);
            }
        }
        if(argList.size()==0){
            return null;
        }else if(argList.size()==1){
            return argList.get(0);
        }else{
            return argList;
        }
    }
}
