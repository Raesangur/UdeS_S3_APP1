package menufact.plats.builder;

import menufact.plats.PlatAuMenu;
import menufact.plats.Recette;

public class PlatBuilder {
    protected PlatAuMenu plat;

    public PlatBuilder() {
        plat = new PlatAuMenu();
    }

    public PlatAuMenu getResult() {
        return plat;
    }

    public PlatBuilder buildDescription(String description) {
        plat.setDescription(description);
        return this;
    }

    public PlatBuilder buildPrix(double prix) {
        plat.setPrix(prix);
        return this;
    }

    public PlatBuilder buildRecette(Recette recette) {
        plat.setRecette(recette);
        return this;
    }
}