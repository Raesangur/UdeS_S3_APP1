package menufact.ingredients.Factory;

import menufact.ingredients.Etat.EtatIngredient;
import menufact.ingredients.Fruit;
import menufact.ingredients.Ingredient;

public class ConcreteCreatorFruit implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Fruit(nom, etat);
    }
}
