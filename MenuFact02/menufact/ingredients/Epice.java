package menufact.ingredients;

public class Epice extends Ingredient {
    public Epice(String nom, EtatIngredient etat) {
        this.etat = etat;
    }
    public Epice(String nom, EtatIngredient etat, double qty) {
        this.etat = etat;
        this.etat.setQty(qty);
    }
}