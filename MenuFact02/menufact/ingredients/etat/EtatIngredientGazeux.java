package menufact.ingredients.etat;

public class EtatIngredientGazeux implements EtatIngredient {
    private double qtyL;

    public EtatIngredientGazeux(double qty) {
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
}

