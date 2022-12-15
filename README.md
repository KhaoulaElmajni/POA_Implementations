# <strong style="color:blue; opacity: 0.80">Activité Pratique Programmation Orientée Aspect</strong>:mortar_board::computer: 
# <span style="color:green "> 1.Présentation de l'activité pratique</span>
# **Partie I (AspectJ)**
 * <strong style="color:dark"> On souhaite créer une application qui permet de gérer des comptes bancaires stockés en mémoire dans une collection de type Map. 
	Chaque compte est défini par son code et son solde.
* Les exigences fonctionnelle de l’application sont:
	* Ajouter un compte
	* Consulter un compte
	* Verser un montant dans un compte
	* Retirer un montant d’un compte
* Les exigences techniques seront implémentées sous formes d’aspects suivants:
	* Un aspect pour la journalisation des appels de toutes les méthodes en affichant la durée d’exécution de chaque méthode
	* Un aspect pour contrôler le montant du retrait
	* Un aspect pour sécuriser l’application
</span>
# **Partie 2 (Spring AOP)**
* <strong style="color:dark"> On souhaite créer une application qui offrent deux
fonctionnalités métiers basiques:
	* Une opération process() permettant d’effectuer un traitement quelconque
	* Une opération permettant de retourner un résultat de calcul quelconque.
	
* Nous définissons dans cette couche métier :
	* Une interface IMetier
	* Une implémentation de cette interface
* Ensuite nous définissons deux aspects basés sur Spring AOP
	* Un Aspect pour la journalisation avec un annotation @Log qui permet de marquer dans la couche la méthode à journaliser
	* Un Aspect pour sécuriser l’application avec un authentification basique avec des rôles. Pour sécuriser l’accès à une méthode, nous définissons une annotation @SecuredByAspect(roles=["ADMIN","USER"]) qui sera placée sur les méthodes à sécuriser en spécifiant les rôles requis.

 # <span style="color:green "> 2.Architecture de l'activité pratique</span>
 
# Partie 1 (AspectJ):
![](https://i.imgur.com/SUJ0zkZ.png)

# Partie 2 (Spring AOP):
 ![](https://i.imgur.com/eP9B9As.png)
 
# Partie 1: (AspectJ)
## AspectJ

![](https://i.imgur.com/vJ9QkWw.png)


 <span style="color:#66ff66"> Entités et règles de gestion : :label: </span>
 * Une entité "Compte"

```java!
public class Compte {
    private Long code;
    private double solde;

    public Compte(Long code, double solde) {
        this.code = code;
        this.solde = solde;
    }

    public Compte() {
    }

    public Long getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "code=" + code +
                ", solde=" + solde +
                '}';
    }
}

```
Interface "IMetier"
```java!
public interface IMetierBanque {
    void addCompte(Compte cp);
    void verser(Long code,double montant);
    void retirer(Long code,double montant);
    Compte consulter(Long code);
}
```

Implémentation de "IMetier"
```java!

public class MetierBanqueImpl implements IMetierBanque {
   private Map<Long,Compte> compteMap=new HashMap<>();

    @Override
    public void addCompte(Compte cp) {
        compteMap.put(cp.getCode(),cp);
    }

    @Override
    public void verser(Long code, double montant) {
        Compte compte=compteMap.get(code);
        compte.setSolde(compte.getSolde()+montant);
    }

    @Override
    public void retirer(Long code, double montant) {
        Compte compte=compteMap.get(code);
        compte.setSolde(compte.getSolde()-montant);

    }

    @Override
    public Compte consulter(Long code) {
        return compteMap.get(code);
    }
}
```

> Aspect 1:

```java!
public aspect FirstAspect {

    //pointcut for the method "main" to be intercepted during execution
    pointcut pc1() : execution(* me..test.Application.main1(..));

    /*//before pointcut pc1 ==> code to be executed before the method "main" is executed ==> code advice
    before():pc1() {
        System.out.printf("--------------------------------------------------------");
        System.out.println("Before the main method from Aspect with AspectJ syntax");
        System.out.printf("--------------------------------------------------------");
    }

    //after pointcut pc1 ==> code to be executed after the method "main" is executed ==> code advice
    after():pc1() {
        System.out.printf("--------------------------------------------------------");
        System.out.println("After the main method from Aspect with AspectJ syntax");
        System.out.printf("--------------------------------------------------------");
    }*/

    void around():pc1(){
        System.out.println("------------------------------------------------");
        System.out.println("before main from aspectj with aspectj syntax");
        System.out.println("------------------------------------------------");

        //Execution de l'operation du pointcut
        //execution(* test.Application.main(..))
        proceed();
        System.out.println("------------------------------------------------");
        System.out.println("after main from aspectj with aspectj syntax");
        System.out.println("------------------------------------------------");

    }
}

```

>Aspect 2

```java!
@Aspect
public class SecondAspect {
    @Pointcut("execution(* me..test.*.main1(..))")
    public void pc1(){ }

    //code advice

   /* @Before("pc1()")
    public void beforeMain(){

        System.out.println("----------****************--------------------------------------");
        System.out.println("before main from aspectj with class syntax");
        System.out.println("-----------*****************-------------------------------------");

    }
    @After("pc1()")
    public void afterMain(){

        System.out.println("----------****************--------------------------------------");
        System.out.println("after main from aspectj with class syntax");
        System.out.println("-----------*****************-------------------------------------");

    }*/

    @Around("pc1()")
    public void aroundMain(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("----------****************--------------------------------------");
        System.out.println("before main from aspectj with class syntax");
        System.out.println("-----------*****************-------------------------------------");
        //execute main
        proceedingJoinPoint.proceed();
        System.out.println("----------****************--------------------------------------");
        System.out.println("after main from aspectj with class syntax");
        System.out.println("-----------*****************-------------------------------------");

    }
}

```
> Aspect de journalisation : 

```java!
@Aspect
public class SecondAspect {
    @Pointcut("execution(* me..test.*.main1(..))")
    public void pc1(){ }

    //code advice

   /* @Before("pc1()")
    public void beforeMain(){

        System.out.println("----------****************--------------------------------------");
        System.out.println("before main from aspectj with class syntax");
        System.out.println("-----------*****************-------------------------------------");

    }
    @After("pc1()")
    public void afterMain(){

        System.out.println("----------****************--------------------------------------");
        System.out.println("after main from aspectj with class syntax");
        System.out.println("-----------*****************-------------------------------------");

    }*/

    @Around("pc1()")
    public void aroundMain(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("----------****************--------------------------------------");
        System.out.println("before main from aspectj with class syntax");
        System.out.println("-----------*****************-------------------------------------");
        //execute main
        proceedingJoinPoint.proceed();
        System.out.println("----------****************--------------------------------------");
        System.out.println("after main from aspectj with class syntax");
        System.out.println("-----------*****************-------------------------------------");

    }
}

```

> Aspect de Retrait

```java!
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

```

>Aspect de sécurité

```java!
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

```

> Résultat:

![](https://i.imgur.com/RylElqB.png)


# Partie 2: (Spring AOP) 

## Spring AOP
![](https://i.imgur.com/MQpaufy.png)


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
> Aspect d'Authorisation
 
```java!

@Component
@Aspect
@EnableAspectJAutoProxy
public class AuthorizationAspect {
    @Around(value = "@annotation(securedByAspect)", argNames = "pjp,securedByAspect")
    public Object secure(ProceedingJoinPoint pjp, SecuredByAspect securedByAspect) throws Throwable {
        String[] roles= securedByAspect.roles();
        boolean authorized=false;
        for (String r:roles) {
            if (SecurityContext.hasRole(r)){
                authorized=true;
                break;
            }
        }
        if (authorized==true){
            Object result = pjp.proceed();
            return result;
        }
        throw new RuntimeException("403 Unauthorized to acces to !"+pjp.getSignature());
    }
}

```
> Application 

```java!
@ComponentScan(value = {"me.elmajni"})
public class Application {
    public static void main(String[] args) {

        try {
            SecurityContext.authenticate("root","123",new String[]{"USER","ADMIN"});
            //SecurityContext.authenticate("rot","123",new String[]{"USER","ADMIN"});
            //SecurityContext.authenticate("root","123",new String[]{"USER"});

            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
            IMetier metier = applicationContext.getBean(IMetier.class);
            System.out.println("*******************");
            System.out.println(metier.getClass().getName());
            System.out.println("*******************");

            metier.process();
            System.out.println(metier.compute());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

   // @Configuration
    //@ComponentScan(value = {"me.elmajni"})
    public class MyConfig{

    }
}
```
> Résultat:

![](https://i.imgur.com/HbGaEyg.png)

 
 # <span style="color:green">3.Les Technologies utilisées</span>
 #### <span style="color:#0036ad"> 1.Spring Core</span>
 * <strong style="color:dark">* <strong style="color:dark">Core (spring-core) est le cœur du framework qui alimente des fonctionnalités telles que l'inversion de contrôle et l'injection de dépendances.

*voir également à propos* [Spring Core](https://www.bmc.com/blogs/spring-framework/#:~:text=Core%20container&text=Core%20(spring%2Dcore)%20is,implementation%20of%20the%20factory%20pattern.):link: 

	
#### <span style="color:#0036ad"> 1.Spring Context</span>
* <strong style="color:dark">* <strong style="color:dark">Les contextes Spring sont également appelés conteneurs Spring IoC, qui sont responsables de l'instanciation, de la configuration et de l'assemblage des beans en lisant les métadonnées de configuration à partir des annotations XML, Java et/ou du code Java dans les fichiers de configuration.

*voir également à propos* [Spring Context](https://dzone.com/articles/what-is-a-spring-context#:~:text=Spring%20contexts%20are%20also%20called,code%20in%20the%20configuration%20files.):link: 

 #### <span style="color:#0036ad"> 1.Spring Context</span>
* <strong style="color:dark">* <strong style="color:dark">Dans Spring AOP, les aspects sont implémentés à l'aide de classes régulières (l'approche basée sur un schéma) ou de classes régulières annotées avec l'annotation @Aspect.

*voir également à propos* [Spring Aspects](https://docs.spring.io/spring-framework/docs/2.5.5/reference/aop.html):link: 	


	
> Created by :[name=ELMAJNI KHAOULA]
[time=Mon,2022,11,01][color=#EF0101]
>*voir également à propos de moi* [ELMAJNI Khaoula](https://www.linkedin.com/in/khaoula-elmajni/)