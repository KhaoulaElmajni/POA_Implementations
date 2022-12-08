package me.elmajni.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LoggingAspect {

    //=>Logger pour les methodes
    Logger logger=Logger.getLogger(LoggingAspect.class.getName());

    public LoggingAspect() throws IOException {
        //=>ajouter un handler pour le fichier de log
        logger.addHandler(new FileHandler("log.xml"));
        //desactiver la journalisation sur la console
        logger.setUseParentHandlers(false);
    }
   // long t1,t2;

    // * ==> type retour ; metier ==> package ; * ==> toutes les classe ; * ==> toute les methodes ; .. ==> toutes les parametre
    //@Pointcut("execution(* metier.MetierBanqueImpl.*(..) ) || initialization(metier.MetierBanqueImpl.new(..)") ==> initialization not seported
    @Pointcut("execution(* me..metier.MetierBanqueImpl.*(..) ) ")
    public void pc1(){ }
/*    @Before("pc1()")
    public void avant(JoinPoint joinPoint){
        //temps a l'entre
        t1=System.currentTimeMillis();
        logger.info("----------------------------------------------------");
        logger.info("Avant l'exécution de la methode "+joinPoint.getSignature());
    }
    @After("pc1()")
    public void apres(JoinPoint joinPoint){
        logger.info("aprés execution de la methode "+joinPoint.getSignature());
        t2=System.currentTimeMillis();
        logger.info("Duree d'execution de la methode :"+ (t2-t1));
        logger.info("----------------------------------------------------");
    }*/
    @Around("pc1()")
    public Object autour(ProceedingJoinPoint proceedingJoinPoint,JoinPoint joinPoint) throws Throwable {

        long t1=System.currentTimeMillis();
        logger.info("----------------------------------------------------");
        logger.info("Avant l'exécution de la methode "+joinPoint.getSignature());
        Object resultat=proceedingJoinPoint.proceed();
        logger.info("aprés l'exécution de la methode "+joinPoint.getSignature());
        long t2=System.currentTimeMillis();
        logger.info("Durée d'exécution de la methode :"+ (t2-t1));
        logger.info("----------------------------------------------------");
        return resultat;

    }
}
