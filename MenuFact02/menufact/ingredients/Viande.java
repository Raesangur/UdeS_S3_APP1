package menufact.ingredients;

import menufact.ingredients.Etat.EtatIngredient;

public class Viande extends Ingredient {
    public Viande(String nom, EtatIngredient etat) {
        this.etat = etat;
    }
    public Viande(String nom, EtatIngredient etat, double qty) {
        this.etat = etat;
        this.etat.setQty(qty);
    }
}
