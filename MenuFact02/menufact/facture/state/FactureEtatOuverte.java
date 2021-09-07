package menufact.facture.state;

public class FactureEtatOuverte implements FactureEtat {
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return etat2 instanceof FactureEtatFermee || etat2 instanceof FactureEtatPayee;
    }
}
