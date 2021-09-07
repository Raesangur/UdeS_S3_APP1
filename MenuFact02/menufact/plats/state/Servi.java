package menufact.plats.state;

public class Servi implements CommandeEtat {
    /**
     * Fonction de changement d'état de commandé à un état adjacent soit Terminé.
     *
     * @param etat
     * @return true si l'état suivant est <code>Termine</code> sinon on retourne false.
     */
    @Override
    public boolean changerEtat(CommandeEtat etat) {
        return etat instanceof ErrorServir;
    }

    /**
     * Affiche le string représentant l'état.
     *
     * @return un string Servi pour l'affichage.
     */
    @Override
    public String toString() {
        return "Servi";
    }
}
