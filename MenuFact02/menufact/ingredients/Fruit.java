package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

public class Fruit extends Ingredient {
    /**
     * Constructeur de la catégorie d'ingrédient Fruit
     *
     * @param nom  Nom de l'ingrédient
     * @param etat État physique de l'ingrédient
     * @throws IngredientException Si l'état passé est <code>null</code>
     */
    public Fruit(String nom, EtatIngredient etat) throws IngredientException {
        setNom(nom);
        if(etat != null){
            this.etat = etat;
        }
        else{
            throw new IngredientException("L'État ne peut pas être null");
        }
    }

    /**
     * Constructeur de la catégorie d'ingrédient Fruit
     *
     * @param nom  Nom de l'ingrédient
     * @param etat État physique de l'ingrédient
     * @param qty  Quantité d'ingrédient
     * @throws IngredientException Si l'état passé est <code>null</code>
     * @throws IngredientException Si la quantité est négative.
     */
    public Fruit(String nom, EtatIngredient etat, double qty) throws IngredientException {
        this.etat = etat;
        this.etat.setQty(qty);
    }

    /**
     * Permet de faire une deep-copy d'un ingrédient.
     *
     * @return Une copie de l'ingrédient
     */
    @Override
    public Ingredient makeCopy() {
        try {
            return new Fruit(getNom(), getEtat().makeCopy());
        } catch (IngredientException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convertit l'ingrédient en une <code>String</code> sous représentation JSON.
     *
     * @return Une <code>String</code> représentant l'ingrédient.
     */
    @Override
    public String toString() {
        return "{Ingrédient 'fruit': {\n\t État " + etat + "\n}}";
    }
}
