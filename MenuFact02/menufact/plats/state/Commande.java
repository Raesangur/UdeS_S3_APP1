package menufact.plats.state;

public class Commande implements CommandeEtat {
    @Override
    public boolean changerEtat(CommandeEtat etat) {
        return etat instanceof EnPreparation || etat instanceof ErrorServir;
    }

    @Override
    public String toString() {
        return "Commande";
    }
}
