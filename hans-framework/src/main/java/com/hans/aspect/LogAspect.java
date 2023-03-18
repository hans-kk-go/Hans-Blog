package com.hans.aspect;


import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
@Slf4j
public class LogAspect {

    //切点
    @Pointcut("@annotation(com.hans.annotation.SystemLog)")
    public void pt(){

    }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret = null;
        try {
            handleBefore();
            ret = joinPoint.proceed();
            handleAfter();
        } finally {
//            结束后换行
            log.info("=============End=============" + System.lineSeparator());

        }
        return ret;
    }

    private void handleAfter() {
    }

    private void handleBefore() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //获取被增强方法上的注解对象


        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}",request.getRequestURL());
        // 打印描述信息
//        log.info("BusinessName   : {}", );
//        // 打印 Http method
//        log.info("HTTP Method    : {}", );
//        // 打印调用 controller 的全路径以及执行方法
//        log.info("Class Method   : {}.{}", );
//        // 打印请求的 IP
//        log.info("IP             : {}",);
//        // 打印请求入参
//        log.info("Request Args   : {}",);

    }
}

