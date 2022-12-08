package me.elmajni.test;

import me.elmajni.metier.Compte;
import me.elmajni.metier.IMetierBanque;
import me.elmajni.metier.MetierBanqueImpl;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        //System.out.println("Hello from main app!");
        new Application().start();
    }
    public void start() {
        System.out.println("----------------------------demarrage de l'application----------------------------");
        Scanner scanner=new Scanner(System.in);
        System.out.println("donner le code du compte:");
        Long code=scanner.nextLong();
        System.out.println("donner le solde initial du compte:");
        double solde=scanner.nextDouble();

        IMetierBanque metierBanque=new MetierBanqueImpl();
        metierBanque.addCompte(new Compte(code,solde));
        while (true){
            try {
                System.out.println("Type opération : ");
                String type=scanner.next();
                if(type.equals("quitter")) break;
                System.out.println("Montant : ");
                double montant=scanner.nextDouble();
                if(type.equals("v")){
                    metierBanque.verser(code,montant);
                }else  if(type.equals("r")){
                    metierBanque.retirer(code,montant);
                }
                Compte c=metierBanque.consulter(code);
                System.out. println("Etat du compte = "+c.toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.printf("----------------------------Fin de l'application----------------------------");
    }
}
