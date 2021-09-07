package menufact.plats.state;

public class Commande implements CommandeEtat {
    /**
     * Fonction de changement d'état de commandé à un état adjacent donc soit EnPréparation ou ErrorServir.
     *
     * @param etat
     * @return true si l'état suivant est <code>EnPreparation</code> ou <code>ErrorServir</code> sinon on retourne false.
     */
    @Override
    public boolean changerEtat(CommandeEtat etat) {
        return etat instanceof EnPreparation || etat instanceof ErrorServir;
    }

    /**
     * Fonction d'affichage de l'état dans un string
     *
     * @return un text dissant <code>Commande</code>
     */
    @Override
    public String toString() {
        return "Commande";
    }
}
