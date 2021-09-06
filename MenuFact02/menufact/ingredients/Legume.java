package menufact.ingredients;

import menufact.ingredients.Etat.EtatIngredient;

public class Legume extends Ingredient {
    public Legume(String nom, EtatIngredient etat) {
        this.etat = etat;
    }
    public Legume(String nom, EtatIngredient etat, double qty) {
        this.etat = etat;
        this.etat.setQty(qty);
    }
}
