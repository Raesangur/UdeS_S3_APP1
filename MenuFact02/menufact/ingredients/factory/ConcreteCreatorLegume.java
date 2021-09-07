package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Legume;
import menufact.ingredients.exceptions.IngredientException;

public class ConcreteCreatorLegume implements CreatorIngredient {
    @Override
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Legume(nom, etat);
    }
}
