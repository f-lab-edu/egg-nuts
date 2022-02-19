package me.usermanagement.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void cut() {
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            log.info("type : {}", obj.getClass().getSimpleName());
            log.info("value : {}", obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "responseEntity")
    public void afterReturn(JoinPoint joinPoint, Object responseEntity) {
        // TODO - Logging을 System.out으로 하면 안되는 이유
//        System.out.println("responseEntity");
//        System.out.println(responseEntity);
        log.info("responseEntity : {}", responseEntity);
    }
}
