package menufact.ingredients.etat;

import menufact.ingredients.exceptions.IngredientException;

public interface EtatIngredient {
    public double getQty();
    public void setQty(double qty) throws IngredientException;

    public EtatIngredient copy();
}
