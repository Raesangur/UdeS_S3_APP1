package menufact.facture.state;

public class FactureEtatPayee implements FactureEtat {
    /**
     * Vérifie si on peut passer de l'état Payée vers un nouvel état.
     * @param etat2 État vers lequel on désire changer.
     * @return false car il n'y a pas d'état apres payee
     */
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return false;
    }
}
