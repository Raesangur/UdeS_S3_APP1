package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Laitier;

public class ConcreteCreatorLaitier implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Laitier(nom, etat);
    }
}
