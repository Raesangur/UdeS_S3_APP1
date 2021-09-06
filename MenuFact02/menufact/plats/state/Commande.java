package menufact.plats.state;

public class Commande implements CommandeEtat {
    public boolean changerEtat(CommandeEtat etat){
        return etat instanceof EnPreparation || etat instanceof ErrorServir;
    };
}
