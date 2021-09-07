package menufact.plats.state;

public class ErrorServir implements CommandeEtat{
    /**
     * Fonction de changement d'état de commandé à un état adjacent dans ce cas il n'y en a pas.
     * @param etat
     * @return toujours false puisqu'il n'y a pas d'état suite a une imposibilité.
     */
    @Override
    public boolean changerEtat(CommandeEtat etat){
        return false;
    }

    /**
     * Affiche l'état avec un string
     * @return un string "Erreur".
     */
    @Override
    public String toString() {
        return "Erreur";
    }
}
