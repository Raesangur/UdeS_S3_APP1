package menufact.ingredients;

public class Fruit extends Ingredient {
    public Fruit(String nom, EtatIngredient etat) {
        this.etat = etat;
    }
    public Fruit(String nom, EtatIngredient etat, double qty) {
        this.etat = etat;
        this.etat.setQty(qty);
    }
}
