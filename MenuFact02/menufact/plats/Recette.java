package menufact.plats;

import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

import java.util.Arrays;
import java.util.ArrayList;

public class Recette {
    private ArrayList<Ingredient> ingredients;

    /**
     * Constructeur de recette avec un array list d'ingrédient
     * @param ingredients Arraylist d'ingrédient
     */
    public Recette(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Constructeur de recette avec un tableau d'ingrédient
     * @param ingredients Tableau d'ingrédient
     */
    public Recette(Ingredient[] ingredients) {
        this.ingredients = new ArrayList<>(Arrays.asList(ingredients));
    }

    /**
     * Donne la liste de tout les ingrédients.
     * @return ArrayList d'ingrédients
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Permet de set une arrayList d'ingrédient à place de celle actuel
     * @param ingredients Arraylist d'ingrédient
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Permet de set un tableau d'ingrédient dans l'arraylist
     * @param ingredients tableau d'ingrédient
     */
    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = new ArrayList<>();
        for (Ingredient ing : ingredients) {
            this.ingredients.add(ing.makeCopy());
        }
    }

    /**
     * Ajoute un ingrédient à l'arrayList d'ingrédient
     * @param ingredient à ajouté à la list
     * @return La recette complete
     */
    // retourne une instance de recette pour pouvoir être utilisé comme un Builder
    public Recette addIngredient(Ingredient ingredient) {
        // Vérifie si l'ingrédient est déjà dans la recette
        for (Ingredient ing : ingredients) {
            if (ing.getNom().equals(ingredient.getNom())) {
                try {
                    ing.setQty(ing.getQty() + ingredient.getQty());
                } catch (IngredientException ie) {
                    // Ne touche pas à la quantité d'ingrédients
                } finally {
                    return this;
                }
            }
        }
        ingredients.add(ingredient);
        return this;
    }

    /**
     * Créer une copie de la recette
     * @return Une copie de la Recette
     */
    public Recette makeCopy() {
        ArrayList<Ingredient> nouvelleListe = new ArrayList<>();
        for (Ingredient ing : ingredients) {
            nouvelleListe.add(ing.makeCopy());
        }
        return new Recette(nouvelleListe);
    }

    /**
     * Affiche la recette sous forme de string contenant tous les incrédients
     * @return String de la recette
     */
    @Override
    public String toString() {
        return "Recette: " + ingredients;
    }

    /**
     * Permet d'égaler une recette avec une autre
     * @param other Un objet
     * @return boolean True si c'est identique false sinon
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Recette) {
            if (((Recette) other).getIngredients().size() != getIngredients().size()) {
                return false;
            } else {
                return getIngredients().equals(((Recette) other).getIngredients());
            }
        } else {
            return false;
        }
    }
}
