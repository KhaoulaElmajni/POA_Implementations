package me.elmajni.service;

import me.elmajni.aspects.Log;
import me.elmajni.aspects.SecuredByAspect;
import org.springframework.stereotype.Service;

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
