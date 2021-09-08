package menufact.plats;

import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

import java.util.Arrays;
import java.util.ArrayList;

public class Recette {
    private ArrayList<Ingredient> ingredients;

    public Recette(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Recette(Ingredient[] ingredients) {
        this.ingredients = new ArrayList<>(Arrays.asList(ingredients));
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = new ArrayList<>();
        for (Ingredient ing : ingredients) {
            this.ingredients.add(ing.makeCopy());
        }
    }

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

    public Recette makeCopy() {
        ArrayList<Ingredient> nouvelleListe = new ArrayList<>();
        for (Ingredient ing : ingredients) {
            nouvelleListe.add(ing.makeCopy());
        }
        return new Recette(nouvelleListe);
    }

    @Override
    public String toString() {
        return "Recette: " + ingredients;
    }

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
