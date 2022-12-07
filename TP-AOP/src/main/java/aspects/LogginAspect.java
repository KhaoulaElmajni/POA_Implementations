package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Aspect
public class LogginAspect {

    //voir les msg en rouge
    Logger logger=Logger.getLogger(LogginAspect.class.getName());

    public LogginAspect() throws IOException {
        logger.addHandler(new FileHandler("log.xml"));
        //desactiver la journalisation sur la console
        logger.setUseParentHandlers(false);
    }
   // long t1,t2;

    // * ==> type retour ; metier ==> package ; * ==> toutes les classe ; * ==> toute les methodes ; .. ==> toutes les parametre
    //@Pointcut("execution(* metier.MetierBanqueImpl.*(..) ) || initialization(metier.MetierBanqueImpl.new(..)") ==> initialization not seported
    @Pointcut("execution(* metier.MetierBanqueImpl.*(..) ) ")
    public void pc1(){ }
/*    @Before("pc1()")
    public void avant(JoinPoint joinPoint){
        //temps a l'entre
        t1=System.currentTimeMillis();
        logger.info("----------------------------------------------------");
        logger.info("Avant execution de la methode "+joinPoint.getSignature());
    }
    @After("pc1()")
    public void apres(JoinPoint joinPoint){
        logger.info("apress execution de la methode "+joinPoint.getSignature());
        t2=System.currentTimeMillis();
        logger.info("Duree d'execution de la methode :"+ (t2-t1));
        logger.info("----------------------------------------------------");

    }*/
    @Around("pc1()")
    public Object autour(ProceedingJoinPoint proceedingJoinPoint,JoinPoint joinPoint) throws Throwable {

        long t1=System.currentTimeMillis();
        logger.info("----------------------------------------------------");
        logger.info("Avant execution de la methode "+joinPoint.getSignature());
        Object resultat=proceedingJoinPoint.proceed();
        logger.info("apress execution de la methode "+joinPoint.getSignature());
        long t2=System.currentTimeMillis();
        logger.info("Duree d'execution de la methode :"+ (t2-t1));
        logger.info("----------------------------------------------------");
        return resultat;



    }
}
