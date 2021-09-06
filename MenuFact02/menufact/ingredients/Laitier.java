package menufact.ingredients;

import menufact.ingredients.Etat.EtatIngredient;

public class Laitier extends Ingredient {
    public Laitier(String nom, EtatIngredient etat) {
        this.etat = etat;
    }
    public Laitier(String nom, EtatIngredient etat, double qty) {
        this.etat = etat;
        this.etat.setQty(qty);
    }
}

