package menufact.plats;

import menufact.plats.exception.PlatException;
import menufact.plats.state.CommandeEtat;

public class PlatChoisi {
    private PlatAuMenu plat;
    private int quantite;
    private CommandeEtat etat;
    public PlatChoisi(PlatAuMenu plat, int quantite) {
        this.plat = plat;
        this.quantite = quantite;
        this.etat = null;
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatChoisi{" +
                "quantite=" + quantite +
                ", plat=" + plat +
                '}';
    }

    public int getQty(){
        return quantite;
    }

    public void setQty(int quantite) {
        this.quantite = quantite;
    }

    public PlatAuMenu getPlat() {
        return plat;
    }

    public CommandeEtat getEtat(){
        return etat;
    };

    public void setEtat(CommandeEtat etat2)  throws PlatException{
        // Vérifie que l'état peut changer vers le nouvel état
        if(etat == null) {
            etat = etat2;
        }
        if(etat.changerEtat(etat2)){
            this.etat = etat2;
        }
        else{
            throw new PlatException("Imposibilite de changer vers cette etat!!! :(");
        }
    }
}
