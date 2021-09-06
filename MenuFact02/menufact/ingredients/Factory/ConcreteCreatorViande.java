package menufact.ingredients.Factory;

import menufact.ingredients.Etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Viande;

public class ConcreteCreatorViande implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Viande(nom, etat);
    }
}
