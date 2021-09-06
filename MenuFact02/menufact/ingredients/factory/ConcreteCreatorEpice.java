package menufact.ingredients.factory;

import menufact.ingredients.Epice;
import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;

public class ConcreteCreatorEpice implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Epice(nom, etat);
    }
}
