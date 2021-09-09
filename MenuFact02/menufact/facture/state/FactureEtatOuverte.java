package menufact.facture.state;

public class FactureEtatOuverte implements FactureEtat {
    /**
     * Vérifie si on peut passer de l'état Ouverte vers un nouvel état.
     * @param etat2 État vers lequel on désire changer.
     * @return      <code>true</code> si le nouvel état est Ouvert ou Payée.
     *              <code>false</code>> autrement.
     */
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return etat2 instanceof FactureEtatFermee || etat2 instanceof FactureEtatPayee;
    }
}
