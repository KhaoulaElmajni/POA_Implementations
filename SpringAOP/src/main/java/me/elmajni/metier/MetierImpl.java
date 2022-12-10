package me.elmajni.metier;


import me.elmajni.aspects.Log;
import me.elmajni.aspects.SecuredByAspect;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier {
    @Override
    @Log
    public void process() {

        System.out.println("Business Processing ");
    }

    @Override
    @SecuredByAspect(roles = {"ADMIN","USER"})
    public double compute() {
        double data =80;
        System.out.println("Businnes Computing and Retirning Result");
        return data;
    }
}
