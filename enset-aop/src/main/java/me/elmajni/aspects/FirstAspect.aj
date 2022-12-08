package me.elmajni.aspects;

public aspect FirstAspect {

    //pointcut for the method "main" to be intercepted during execution
    pointcut pc1() : execution(* me..test.Application.main(..));

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
