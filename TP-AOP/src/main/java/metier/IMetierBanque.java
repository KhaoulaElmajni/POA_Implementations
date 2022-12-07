package metier;

public interface IMetierBanque {
     void addCompt(Compte cp);
    void verser(Long code,double montant);
    void retirer(Long code,double montant);
    Compte consulter(Long code);
}
