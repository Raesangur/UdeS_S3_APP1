package menufact.plats;

import menufact.plats.exception.PlatException;
import menufact.plats.state.CommandeEtat;

public class PlatChoisi {
    private PlatAuMenu plat;
    private int quantite;
    private CommandeEtat etat;

    public PlatChoisi(PlatAuMenu plat, int quantite) throws PlatException {
        this.plat = plat;
        if (quantite >= 0) {
            this.quantite = quantite;
        } else {
            throw new PlatException("negative Number");
        }
        this.etat = null;
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatChoisi{" +
                "quantite=" + quantite +
                ", plat=" + plat +
                '}';
    }

    public int getQty() {
        return quantite;
    }

    public void setQty(int quantite) throws PlatException {
        if (quantite >= 0) {
            this.quantite = quantite;
        } else {
            throw new PlatException("negative Number");
        }
    }

    public PlatAuMenu getPlat() {
        return plat;
    }

    public CommandeEtat getEtat() {
        return etat;
    }


    public void setEtat(CommandeEtat etat2) throws PlatException {
        // Vérifie que l'état peut changer vers le nouvel état
        if (etat == null) {
            etat = etat2;
        } else if (etat.changerEtat(etat2)) {
            this.etat = etat2;
        } else {
            throw new PlatException("Imposibilite de changer vers cette etat!!! :(");
        }
    }
}
