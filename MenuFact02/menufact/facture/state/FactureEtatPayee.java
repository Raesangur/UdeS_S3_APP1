package menufact.facture.state;

public class FactureEtatPayee implements FactureEtat {
    @Override
    public boolean changerEtat(FactureEtat etat2) {
        return false;
    }
}
