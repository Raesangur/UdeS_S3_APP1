package menufact.facture.state;

public class FactureEtatFermee implements FactureEtat {
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        if(etat2 instanceof FactureEtatOuverte || etat2 instanceof FactureEtatPayee) {
            return true;
        }
        else {
            return false;
        }
    }
}
