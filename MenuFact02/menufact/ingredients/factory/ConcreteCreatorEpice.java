package menufact.ingredients.factory;

import menufact.ingredients.Epice;
import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

public class ConcreteCreatorEpice implements CreatorIngredient {
    @Override
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Epice(nom, etat);
    }
}
