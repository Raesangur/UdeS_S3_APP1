package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

public class Epice extends Ingredient {
    public Epice(String nom, EtatIngredient etat) throws IngredientException {
        setNom(nom);
        if(etat != null){
            this.etat = etat;
        }
        else{
            throw new IngredientException("L'État ne peut pas être null");
        }
    }

    public Epice(String nom, EtatIngredient etat, double qty) throws IngredientException {
        this.etat = etat;
        this.etat.setQty(qty);
    }

    @Override
    public Ingredient makeCopy() {
        try {
            return new Epice(getNom(), getEtat().makeCopy());
        } catch (IngredientException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "{Ingrédient 'epice': {\n\t État " + etat + "\n}}";
    }
}
