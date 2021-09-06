package menufact.plats.state;

<<<<<<< Updated upstream:MenuFact02/menufact/plats/state/Commande.java
public class Commande implements CommandeEtat {
    @Override
    public boolean changerEtat(CommandeEtat etat){
=======
public class Commander implements CommandeEtat {
    public boolean changerEtat(CommandeEtat etat) {
>>>>>>> Stashed changes:MenuFact02/menufact/plats/state/Commander.java
        return etat instanceof EnPreparation || etat instanceof ErrorServir;
    }

}
