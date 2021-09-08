package menufact.facture.state;

/**
 * Interface de base qui permet d'implémenter le pattern State de la facture.
 */
public interface FactureEtat {
    /**
     * Vérifie si un état peut changer vers un autre état.
     * @param etat2 État vers lequel on désire changer.
     * @return      <code>true</code> si on peut changer vers ce nouvel état.
     *              <code>false</code> si on ne peut pas changer vers ce nouvel état.
     *              <code>false</code> si l'état passé est <code>null</code>
     */
    public boolean changerEtat(FactureEtat etat2);
}
