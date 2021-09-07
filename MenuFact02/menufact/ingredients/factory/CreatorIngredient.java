package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

public interface CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException;
}
