package me.elmajni.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Scanner;

@Aspect
public class SecurityAspect {

    //point de coupage => expression de point de coupage
    @Pointcut("execution(* me..test.Application.start(..))")
    public void startAppPointCut(){};

    //code advice => autour de la methode start
    @Around("startAppPointCut()")
    public void autourStart(ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
        Scanner scanner=new Scanner(System.in);
        System.out.println("username : ");
        String username = scanner.next();
        System.out.println("password : ");
        String password = scanner.next();
        if(username.equals("root") && password.equals("123")){
            proceedingJoinPoint.proceed();
        }else{
            System.out.println("Access Denied ...... ");
        }
    }
}
