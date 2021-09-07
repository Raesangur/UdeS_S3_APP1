package menufact.plats;

import menufact.ingredients.Ingredient;

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
        this.ingredients = new ArrayList<>(Arrays.asList(ingredients));
    }

    // retourne une instance de recette pour pouvoir être utilisé comme un Builder
    public Recette addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    @Override
    public String toString() {
        return "Recette: " + ingredients;
    }
}
