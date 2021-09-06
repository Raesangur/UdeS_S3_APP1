package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Fruit;
import menufact.ingredients.Ingredient;

public class ConcreteCreatorFruit implements CreatorIngredient {
    @Override
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Fruit(nom, etat);
    }
}
