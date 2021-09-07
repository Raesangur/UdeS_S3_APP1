package menufact.plats;

import menufact.ingredients.Ingredient;

import java.util.Arrays;
import java.util.List;

public class Recette {
    private List<Ingredient> ingredients;

    public Recette(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Recette(Ingredient[] ingredients) {
        this.ingredients = Arrays.asList(ingredients);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = Arrays.asList(ingredients);
    }

    public Recette addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        return this;
    }

    @Override
    public String toString() {
        return "Recette: " + ingredients;
    }
}
