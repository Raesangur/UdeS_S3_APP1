package menufact.facture.state;

/**
 * Représente un état de facture fermée.
 */
public class FactureEtatFermee implements FactureEtat {
    /**
     * Vérifie si on peut passer de l'état fermé vers un nouvel état.
     * @param etat2 État vers lequel on désire changer.
     * @return      <code>true</code> si le nouvel état est Ouvert ou Payée.
     *              <code>false</code>> autrement.
     */
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return etat2 instanceof FactureEtatOuverte || etat2 instanceof FactureEtatPayee;
    }
}
