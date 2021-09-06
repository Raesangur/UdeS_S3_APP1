package menufact.ingredients.Factory;

import menufact.ingredients.Etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Laitier;

public class ConcreteCreatorLaitier implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Laitier(nom, etat);
    }
}
