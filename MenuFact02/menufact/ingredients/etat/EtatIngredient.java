package menufact.ingredients.etat;

import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

public interface EtatIngredient {
    public double getQty();
    public void setQty(double qty) throws IngredientException;

    public EtatIngredient makeCopy();

    @Override
    public boolean equals(Object other);
}
