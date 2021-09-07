package menufact.plats.state;

public class EnPreparation implements CommandeEtat {
    /**
     * Fonction de changement d'état de commandé à un état adjacent soit Servi.
     *
     * @param etat
     * @return true si l'état suivant est <code>Servi</code> sinon on retourne false.
     */
    @Override
    public boolean changerEtat(CommandeEtat etat) {
        return etat instanceof Servi || etat instanceof Termine || etat instanceof ErrorServir;
    }

    ;

    /**
     * Fonction d'affichage de l'état dans un string
     *
     * @return un text dissant <code>En Préparation</code>
     */
    @Override
    public String toString() {
        return "En Préparation";
    }
}
