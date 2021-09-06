package menufact.ingredients.Etat;

public class EtatIngredientLiquide implements EtatIngredient {
    private double qtyL;

    public EtatIngredientLiquide(double qty) {
        qtyL = qty;
    }

    public double getQty() {
        return qtyL;
    }
    public void setQty(double qty) {
        qtyL = qty;
    }
}
