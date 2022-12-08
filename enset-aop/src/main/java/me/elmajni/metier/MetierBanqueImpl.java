package me.elmajni.metier;

import java.util.HashMap;
import java.util.Map;

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
