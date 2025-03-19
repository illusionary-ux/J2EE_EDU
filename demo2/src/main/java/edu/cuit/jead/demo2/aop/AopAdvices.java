package edu.cuit.jead.demo2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAdvices {
    @Around("execution(void edu.cuit.jead.demo2.kernel.CompKernel.doIt(String))")
    public Object kernelAdvice(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        String callerName = (String) args[0];
        System.out.println("---------"+callerName+"-----------");
        Object result = pjp.proceed();
        System.out.println("----------------------------------");
        return result;
    }
}
