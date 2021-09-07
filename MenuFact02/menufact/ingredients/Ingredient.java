package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

public abstract class Ingredient {
    protected EtatIngredient etat;
    private String nom;

    public Ingredient() {}
    public Ingredient(Ingredient other) throws IngredientException {
        nom = new String(other.nom);
        etat = other.etat.copy();
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getQty() {
        return etat.getQty();
    }
    public void setQty(double qty) throws IngredientException {
        etat.setQty(qty);
    }
    public EtatIngredient getEtat() {
        return etat;
    }
}
