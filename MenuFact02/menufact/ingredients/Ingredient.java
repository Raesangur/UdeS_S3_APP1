package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

public abstract class Ingredient {
    protected EtatIngredient etat;
    private String nom;

    public Ingredient() {
    }

    public abstract Ingredient makeCopy();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom != null){
            this.nom = nom;
        }

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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Ingredient) {
            return etat.equals(((Ingredient) other).getEtat()) && nom.equals(((Ingredient) other).getNom());
        } else {
            return false;
        }
    }
}
