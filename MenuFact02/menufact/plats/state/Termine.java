package menufact.plats.state;

public class Termine implements CommandeEtat {
    /**
     * Fonction de changement d'état de commandé à un état adjacent dans ce cas il n'y en a pas.
     *
     * @param etat
     * @return toujours false puisqu'il n'y a pas d'état suite a une imposibilité.
     */
    @Override
    public boolean changerEtat(CommandeEtat etat) {
        return etat instanceof Servi || etat instanceof ErrorServir;
    }

    /**
     * Affiche le string représentant l'état.
     *
     * @return Terminé dans un string pour l'affichage.
     */
    @Override
    public String toString() {
        return "Terminé";
    }
}
