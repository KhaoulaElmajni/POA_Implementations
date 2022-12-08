package me.elmajni.aspects;


import me.elmajni.metier.Compte;
import me.elmajni.metier.MetierBanqueImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PathRetraitAspect {

    //Pointcut => expression de point de coupage
    @Pointcut("execution(* me..metier.MetierBanqueImpl.retirer(..) )")
    public void pc1(){ }

    //code advice => around the  method retirer
    @Around("pc1() && args(code,montant)")
    public Object autourRetirer(Long code,double montant,ProceedingJoinPoint proceedingJoinPoint, JoinPoint joinPoint) throws Throwable {
        MetierBanqueImpl metierBanque=(MetierBanqueImpl) joinPoint.getTarget();
        Compte compte=metierBanque.consulter(code);
        if(compte.getSolde()<montant) throw new RuntimeException("solde insuffisant");
        return  proceedingJoinPoint.proceed();
    }
}
