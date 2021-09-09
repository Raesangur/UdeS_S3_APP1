package menufact.plats.builder;

import menufact.ingredients.Ingredient;
import menufact.plats.PlatAuMenu;
import menufact.plats.Recette;
import menufact.plats.exception.PlatException;

public class PlatBuilder {
    protected PlatAuMenu plat;

    /**
     * Créer un builder de plat
     */
    public PlatBuilder() {
        plat = new PlatAuMenu();
    }

    /**
     * Donne le plat final
     * @return PlatAuMenu qui a terminer de build
     */
    public PlatAuMenu getResult() {
        return plat;
    }

    /**
     * Efface le plat en créant un nouveau plat vide
     * @return PlatBuilder actuel après que le plat est été vider.
     */
    public PlatBuilder clear()
    {
        plat = new PlatAuMenu();
        return this;
    }

    /**
     * permet de changer la description du plat
     * @param description du plat de type String
     * @return PlatBuilder avec la description du plat changer
     */
    public PlatBuilder buildDescription(String description) {
        plat.setDescription(description);
        return this;
    }

    /**
     * Permet de changer le prix du plat en train de build
     * @param prix le prix du plat de type double
     * @return le builderactuel avec le prix du plat changer
     * @throws PlatException Throw si le prix est négatif
     */
    public PlatBuilder buildPrix(double prix) throws PlatException {
        plat.setPrix(prix);
        return this;
    }

    /**
     * Permet de changer la recette du plat
     * @param recette Recette de type Recette à lié au plat
     * @return le PlatBuilder actuel avec la recette changer
     * @throws PlatException Throw si la recette est null.
     */
    public PlatBuilder buildRecette(Recette recette) throws PlatException {
        plat.setRecette(recette);
        return this;
    }

    /**
     * Permet de changer la recette d'un plat avec un tableau d'Ingredient
     * @param recette tableau d'ingredient a mettre dans la recette
     * @return le PlatBuilder actuel avec la recette changer
     * @throws PlatException Throw si la recette est null.
     */
    public PlatBuilder buildRecette(Ingredient[] recette) throws PlatException {
        plat.setRecette(new Recette(recette));
        return this;
    }
}
