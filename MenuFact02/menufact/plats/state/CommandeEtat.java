package menufact.plats.state;

public interface CommandeEtat {
    /**
     * Fonction abstraite de l'interface du changement d'état
     * @param etat
     * @return booléen true si l'état suivant demander est posible faux sinon.
     */
    public boolean changerEtat(CommandeEtat etat);
}
