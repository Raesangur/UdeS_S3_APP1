package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

public class Legume extends Ingredient {
    public Legume(String nom, EtatIngredient etat) {
        setNom(nom);
        this.etat = etat;
    }

    public Legume(String nom, EtatIngredient etat, double qty) throws IngredientException {
        this.etat = etat;
        this.etat.setQty(qty);
    }

    @Override
    public Ingredient makeCopy() {
        return new Legume(getNom(), getEtat().makeCopy());
    }

    @Override
    public String toString() {
        return "{Ingrédient 'legume': {\n\t État " + etat + "\n}}";
    }
}
