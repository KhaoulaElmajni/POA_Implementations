package aspects;

public aspect FirstAspect {

    //point cut
    pointcut pc1():execution(* test.Application.mai(..));
   /*
    //code advice
    before():pc1(){
        System.out.println("------------------------------------------------");
        System.out.println("before main from aspectj with aspectj syntax");
        System.out.println("------------------------------------------------");

    }
    after():pc1(){
        System.out.println("------------------------------------------------");
        System.out.println("after main from aspectj with aspectj syntax");
        System.out.println("------------------------------------------------");
    }*/
    void around():pc1(){
        System.out.println("------------------------------------------------");
        System.out.println("before main from aspectj with aspectj syntax");
        System.out.println("------------------------------------------------");

        //Execution de l'operateur du pointcut
        proceed();
        System.out.println("------------------------------------------------");
        System.out.println("after main from aspectj with aspectj syntax");
        System.out.println("------------------------------------------------");


    }
}
