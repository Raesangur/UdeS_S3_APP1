package menufact.ingredients.etat;

import menufact.ingredients.exceptions.IngredientException;

public class EtatIngredientLiquide implements EtatIngredient {
    private double qtyL;

    public EtatIngredientLiquide(double qty) {
        qtyL = qty;
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
    public EtatIngredient copy() {
        return new EtatIngredientLiquide(getQty());
    }

    @Override
    public String toString() {
        return "'Liquide': {\n\t 'Qty (L)': " + qtyL + "\n}";
    }
}
