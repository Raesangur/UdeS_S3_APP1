package menufact.ingredients.etat;

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
    public void setQty(double qty) {
        qtyKg = qty;
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
