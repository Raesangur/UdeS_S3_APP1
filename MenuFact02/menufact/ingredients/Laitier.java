package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

public class Laitier extends Ingredient {
    public Laitier(String nom, EtatIngredient etat) {
        setNom(nom);
        this.etat = etat;
    }

    public Laitier(String nom, EtatIngredient etat, double qty) throws IngredientException {
        this.etat = etat;
        this.etat.setQty(qty);
    }

    @Override
    public Ingredient makeCopy() {
        return new Laitier(getNom(), getEtat().makeCopy());
    }

    @Override
    public String toString() {
        return "{Ingrédient 'laitier': {\n\t État " + etat + "\n}}";
    }
}

