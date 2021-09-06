package menufact.ingredients.Factory;

import menufact.ingredients.Etat.EtatIngredient;
import menufact.ingredients.Ingredient;

public interface CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat);
}
