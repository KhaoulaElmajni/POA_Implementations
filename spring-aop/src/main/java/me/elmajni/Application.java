package me.elmajni;

import me.elmajni.service.IMetier;
import me.elmajni.service.SecurityContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"me.elmajni"})
public class Application {
    public static void main(String[] args) {

        SecurityContext.authenticate("root","123",new String[]{"USER","ADMIN"});

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        IMetier metier = applicationContext.getBean(IMetier.class);
        System.out.println("*******************");
        System.out.println(metier.getClass().getName());
        System.out.println("*******************");

        metier.process();
        System.out.println(metier.compute());
    }
}