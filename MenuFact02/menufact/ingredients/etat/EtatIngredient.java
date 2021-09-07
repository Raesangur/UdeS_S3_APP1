package menufact.ingredients.etat;

public interface EtatIngredient {
    public double getQty();
    public void setQty(double qty);

    public EtatIngredient copy();
}
