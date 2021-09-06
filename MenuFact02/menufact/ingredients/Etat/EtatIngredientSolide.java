package menufact.ingredients.Etat;

public class EtatIngredientSolide implements EtatIngredient {
    private double qtyKg;

    public EtatIngredientSolide(double qty) {
        qtyKg = qty;
    }

    public double getQty() {
        return qtyKg;
    }
    public void setQty(double qty) {
        qtyKg = qty;
    }
}
