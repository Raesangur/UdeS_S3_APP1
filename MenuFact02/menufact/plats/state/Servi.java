package menufact.plats.state;

public class Servi implements CommandeEtat {
    @Override
    public boolean changerEtat(CommandeEtat etat) {
        return etat instanceof Termine;
    }

    @Override
    public String toString() {
        return "Servi";
    }
}
