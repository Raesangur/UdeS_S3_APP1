package menufact.ingredients.etat;

import menufact.ingredients.exceptions.IngredientException;

public class EtatIngredientSolide implements EtatIngredient {
    private double qtyKg;

    public EtatIngredientSolide(double qty) {
        qtyKg = qty;
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
    public EtatIngredient copy()
    {
        return new EtatIngredientSolide(getQty());
    }

    @Override
    public String toString() {
        return "'Solide': {\n\t 'Qty (kg)': " + qtyKg + "\n}";

    }
}
