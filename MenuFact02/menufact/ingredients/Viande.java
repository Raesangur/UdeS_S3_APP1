package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

public class Viande extends Ingredient {
    public Viande(String nom, EtatIngredient etat) {
        setNom(nom);
        this.etat = etat;
    }

    public Viande(String nom, EtatIngredient etat, double qty) throws IngredientException {
        this.etat = etat;
        this.etat.setQty(qty);
    }

    @Override
    public Ingredient makeCopy() {
        return new Viande(getNom(), getEtat().makeCopy());
    }

    @Override
    public String toString() {
        return "{Ingrédient 'viande': {\n\t État " + etat + "\n}}";
    }
}
