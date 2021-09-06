package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;

public interface CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat);
}
