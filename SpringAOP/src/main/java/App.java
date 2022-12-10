import me.elmajni.metier.IMetier;
import me.elmajni.aspects.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) throws Exception{
        aspects.ApplicationContext.authenticatedUser("root","1234",new String[]{"ADMIN"});
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        IMetier metier=context.getBean(IMetier.class);
        metier.process();
        System.out.println(metier.compute());
        System.out.println("--------------------------");
    }
}
