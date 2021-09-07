package menufact.ingredients.etat;

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
    public void setQty(double qty) {
        qtyL = qty;
    }

    @Override
    public EtatIngredient copy()
    {
        return new EtatIngredientLiquide(getQty());
    }

    @Override
    public String toString() {
        return "'Liquide': {\n\t 'Qty (L)': " + qtyL + "\n}";
    }
}
