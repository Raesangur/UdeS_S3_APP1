package menufact.plats;
import menufact.ingredients.Ingredient;
import java.util.ArrayList;

public class Recette {
    private ArrayList<Ingredient> ingredients;

    public Recette(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
