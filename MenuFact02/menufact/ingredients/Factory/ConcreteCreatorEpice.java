package menufact.ingredients.Factory;

import menufact.ingredients.Epice;
import menufact.ingredients.Etat.EtatIngredient;
import menufact.ingredients.Ingredient;

public class ConcreteCreatorEpice implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Epice(nom, etat);
    }
}
