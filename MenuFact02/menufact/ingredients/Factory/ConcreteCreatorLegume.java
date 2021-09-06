package menufact.ingredients.Factory;

import menufact.ingredients.Etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Legume;

public class ConcreteCreatorLegume implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Legume(nom, etat);
    }
}
