package aspects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Produit {
    private double prix;

    public Produit() {
    }

    public Produit(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public static double caclulerTotal(List<Produit> produits){
        double total = 0.0;
        for(int i=0;i<produits.size();i++){
            total = (double) total + produits.get(i).getPrix();
        }
        return total;
    }

    public static double calculerVente(List<Produit> produits){
         double promotion = 0.5;
         return caclulerTotal(produits)*promotion;
    }


    //----------------- Do ------------------//
    int balance = 5000;
    void withdraw(int amount) throws BalanceException {
        if (amount > balance) {
            throw new BalanceException();
        }
        balance -= amount;
    }





    public static void main(String[] args) {
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit(100));
        produits.add(new Produit(70));
        produits.add(new Produit(20));
        System.out.println(Produit.calculerVente(produits));

        Produit p = new Produit();
        System.out.println(p.withdraw(10000));



    }
}