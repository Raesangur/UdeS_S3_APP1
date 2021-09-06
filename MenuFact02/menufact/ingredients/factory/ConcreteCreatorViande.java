package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Viande;

public class ConcreteCreatorViande implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Viande(nom, etat);
    }
}
