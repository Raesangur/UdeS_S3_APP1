package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;

public abstract class Ingredient {
    protected EtatIngredient etat;
    private String nom;

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getQty() {
        return etat.getQty();
    }
    public void setQty(double qty) {
        etat.setQty(qty);
    }
    public EtatIngredient getEtat() {
        return etat;
    }
}
