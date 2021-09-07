package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Viande;
import menufact.ingredients.exceptions.IngredientException;

public class ConcreteCreatorViande implements CreatorIngredient {
    @Override
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Viande(nom, etat);
    }
}
