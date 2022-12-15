# <strong style="color:blue; opacity: 0.80">Activité Pratique Programmation Orientée Aspect</strong>:mortar_board::computer: 
# <span style="color:green "> 1.Présentation de l'activité pratique</span>
 * <strong style="color:dark"> : 
	
</span>
# Partie 1: (AspectJ)

 <span style="color:#66ff66"> Entités et règles de gestion : :label: </span>
 
 
 
 Interface *IMetier*
 
```java!

public interface IMetier {
    public void process();
    public double compute();
   // User saveUser(User user);
}

```
Implementation *IMetier*

```java!

@Service
public class IMetierImpl implements IMetier {
    @Override
    @Log
    @SecuredByAspect(roles={"USER","ADMIN"})
    public void process() {
        System.out.println("Processing...");
    }

    @Override
    @Log
    @SecuredByAspect(roles={"ADMIN"})
    public double compute() {
        double data = 80;
        System.out.println("Computing and Retirning Result");
        return data;
    }
}

```

## Les aspects:

### Aspect de journalisation :100: 

> annotation "Log"
___

```java!

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {

}
```



```java!

@Component
@Aspect
@EnableAspectJAutoProxy
public class LogAspect {

    Logger logger = Logger.getLogger(LogAspect.class.getName());
    @Around("@annotation(me.elmajni.aspects.Log)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        logger.info("From Logging Aspect ... Before "+proceedingJoinPoint.getSignature());

        Object result = proceedingJoinPoint.proceed();
        logger.info("From Logging Aspect ... After "+proceedingJoinPoint.getSignature());
        long t2 = System.currentTimeMillis();
        logger.info("Duration : "+(t2-t1));
        return result;
    }
}

```
### Aspect de Sécurité :100: 

> Annotation "SecuredByAspect"

```java!
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SecuredByAspect {
    String[] roles();
}

```

> Contexte de sécurité:

```java!

public class SecurityContext {
    private static String username="";
    private static String password="";
    private static String[] roles={};


    public static void authenticate(String u, String p, String[] r){
        if (u.equals("root") && p.equals("123")){
            username=u;
            password=p;
            roles=r;
        }else {
            throw new RuntimeException("Access denied");
        }
    }

    public static boolean hasRole(String r){
        for (String role: roles){
            if (role.equals(r))
                return true;
        }
        return false;
    }
}

```

> Classe "User"

```java!
public class User {
    private Long id;
    private String username;
    private String cin;
}
```
 
 # <span style="color:green "> 2.Architecture de l'activité pratique</span>
 
 
 # <span style="color:green">3.Les Technologies utilisées</span>
 #### <span style="color:#0036ad"> 1.Java</span>
 * <strong style="color:dark">* <strong style="color:dark">Java est le langage de choix pour créer des applications à l'aide de code managé qui peut s'exécuter sur des appareils mobiles.

*voir également à propos* [JAVA](https://www.java.com/fr/):link: 


	SPRING CORE
	SPRING CONTEXT
	SPRING ASPECTS
	
	
	

# Partie 2:(Spring AOP)
	
	
> Created by :[name=ELMAJNI KHAOULA]
[time=Mon,2022,11,01][color=#EF0101]
>*voir également à propos de moi* [ELMAJNI Khaoula](https://www.linkedin.com/in/khaoula-elmajni/)