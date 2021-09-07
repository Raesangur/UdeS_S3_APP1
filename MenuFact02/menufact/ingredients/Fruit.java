package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

public class Fruit extends Ingredient {
    public Fruit(String nom, EtatIngredient etat) {
        setNom(nom);
        this.etat = etat;
    }

    public Fruit(String nom, EtatIngredient etat, double qty) throws IngredientException {
        this.etat = etat;
        this.etat.setQty(qty);
    }

    @Override
    public Ingredient makeCopy() {
        return new Fruit(getNom(), getEtat().makeCopy());
    }

    @Override
    public String toString() {
        return "Ingrédient 'fruit': {\n\t État " + etat + "\n}";
    }
}
