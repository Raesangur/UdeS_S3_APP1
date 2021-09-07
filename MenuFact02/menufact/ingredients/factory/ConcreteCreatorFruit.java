package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Fruit;
import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

public class ConcreteCreatorFruit implements CreatorIngredient {
    @Override
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Fruit(nom, etat);
    }
}
