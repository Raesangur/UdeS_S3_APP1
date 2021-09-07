package menufact.ingredients.etat;

import menufact.ingredients.exceptions.IngredientException;

public class EtatIngredientLiquide implements EtatIngredient {
    private double qtyL;

    public EtatIngredientLiquide(double qty) throws IngredientException{
        setQty(qty);
    }

    @Override
    public double getQty() {
        return qtyL;
    }

    @Override
    public void setQty(double qty) throws IngredientException {
        if (qty < 0) {
            throw new IngredientException("Quantité d'ingrédient ne peut pas être négative");
        } else {
            qtyL = qty;
        }
    }

    @Override
    public EtatIngredient makeCopy() {
        try {
            return new EtatIngredientLiquide(getQty());
        } catch (IngredientException ie) {
            // Erreur impossible
            return null;
        }
    }

    @Override
    public String toString() {
        return "'Liquide': {\n\t 'Qty (L)': " + qtyL + "\n}";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof EtatIngredientLiquide) {
            return getQty() - ((EtatIngredient) other).getQty() < 0.05;
        } else {
            return false;
        }
    }
}
