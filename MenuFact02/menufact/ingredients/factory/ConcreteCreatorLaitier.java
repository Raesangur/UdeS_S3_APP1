package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Laitier;
import menufact.ingredients.exceptions.IngredientException;

public class ConcreteCreatorLaitier implements CreatorIngredient {
    @Override
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Laitier(nom, etat);
    }
}
