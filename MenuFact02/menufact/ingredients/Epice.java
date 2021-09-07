package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.etat.EtatIngredientSolide;
import menufact.ingredients.exceptions.IngredientException;

public class Epice extends Ingredient {
    public Epice(String nom, EtatIngredient etat) {
        setNom(nom);
        this.etat = etat;
    }

    public Epice(String nom, EtatIngredient etat, double qty) throws IngredientException {
        this.etat = etat;
        this.etat.setQty(qty);
    }

    @Override
    public Ingredient makeCopy() {
        return new Epice(getNom(), getEtat().makeCopy());
    }

    @Override
    public String toString() {
        return "Ingrédient 'epice': {\n\t État " + etat + "\n}";
    }
}
