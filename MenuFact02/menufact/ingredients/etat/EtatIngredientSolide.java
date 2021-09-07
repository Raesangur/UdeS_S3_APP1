package menufact.ingredients.etat;

import menufact.ingredients.exceptions.IngredientException;

public class EtatIngredientSolide implements EtatIngredient {
    private double qtyKg;

    public EtatIngredientSolide(double qty) throws IngredientException {
        setQty(qty);
    }

    @Override
    public double getQty() {
        return qtyKg;
    }

    @Override
    public void setQty(double qty) throws IngredientException {
        if (qty < 0) {
            throw new IngredientException("Quantité d'ingrédient ne peut pas être négative");
        } else {
            qtyKg = qty;
        }
    }

    @Override
    public EtatIngredient makeCopy() {
        try {
            return new EtatIngredientSolide(getQty());
        } catch (IngredientException ie) {
            // Erreur impossible
            return null;
        }
    }

    @Override
    public String toString() {
        return "'Solide': {\n\t 'Qty (kg)': " + qtyKg + "\n}";

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof EtatIngredientSolide) {
            return getQty() - ((EtatIngredient) other).getQty() < 0.05;
        } else {
            return false;
        }
    }
}
