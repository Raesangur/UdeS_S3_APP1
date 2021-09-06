package menufact.ingredients;

public class EtatIngredientGazeux implements EtatIngredient {
    private double qtyL;

    public EtatIngredientGazeux(double qty) {
        qtyL = qty;
    }

    public double getQty() {
        return qtyL;
    }
    public void setQty(double qty) {
        qtyL = qty;
    }
}

