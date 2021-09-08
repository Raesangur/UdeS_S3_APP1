package menufact.plats.builder;

import menufact.ingredients.Ingredient;
import menufact.plats.PlatAuMenu;
import menufact.plats.Recette;
import menufact.plats.exception.PlatException;

public class PlatBuilder {
    protected PlatAuMenu plat;

    public PlatBuilder() {
        plat = new PlatAuMenu();
    }

    public PlatAuMenu getResult() {
        return plat;
    }

    public PlatBuilder clear()
    {
        plat = new PlatAuMenu();
        return this;
    }

    public PlatBuilder buildDescription(String description) {
        plat.setDescription(description);
        return this;
    }

    public PlatBuilder buildPrix(double prix) throws PlatException {
        plat.setPrix(prix);
        return this;
    }

    public PlatBuilder buildRecette(Recette recette) throws PlatException {
        plat.setRecette(recette);
        return this;
    }

    public PlatBuilder buildRecette(Ingredient[] recette) throws PlatException {
        plat.setRecette(new Recette(recette));
        return this;
    }
}
