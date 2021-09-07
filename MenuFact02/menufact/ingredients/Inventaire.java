package menufact.ingredients;

import menufact.ingredients.exceptions.IngredientException;
import menufact.plats.Recette;
import menufact.plats.builder.PlatBuilder;

import java.util.HashMap;

public class Inventaire {
    private static Inventaire instance;
    private HashMap<String, Ingredient> congelateur;

    public static synchronized Inventaire getInstance() {
        return instance == null ? instance = new Inventaire() : instance;
    }

    private Inventaire() {
        congelateur = new HashMap<>();
    }

    public void ajouterIngredient(Ingredient[] ingredients) throws IngredientException {
        // Si l'ingrédient est déjà dans l'inventaire, ajoute la quantité à l'ingrédient existant
        for(Ingredient ingredient : ingredients)
        {
            ajouterIngredient(ingredient);
        }
    }
    public void ajouterIngredient(Ingredient ingredient) throws IngredientException {
        // Si l'ingrédient est déjà dans l'inventaire, ajoute la quantité à l'ingrédient existant
        if (congelateur.containsKey(ingredient.getNom())) {
            Ingredient ing = congelateur.get(ingredient.getNom());
            ing.setQty(ing.getQty() + ingredient.getQty());
        } else {
            congelateur.put(ingredient.getNom(), ingredient);
        }
    }

    public Ingredient getIngredient(String nomIngredient) {
        return congelateur.get(nomIngredient);
    }

    public void consommerRecette(Recette recette, int qtyPlats, double proportion) throws IngredientException {
        // Parcours tous les plats
        for (Ingredient ingredientRecette : recette.getIngredients()) {
            Ingredient ingredientCongelateur = congelateur.get(ingredientRecette.getNom());

            // Vérifie que l'ingrédient existe dans le congélateur (on ne devrait jamais lancer cette exception)
            if (ingredientCongelateur == null) {
                throw new IngredientException("Ingrédient n'existe pas dans l'inventaire");
            }
            double qtyRecette = ingredientRecette.getQty() * qtyPlats * proportion;
            double qtyInventaire = ingredientCongelateur.getQty();

            // Vérifie que la quantité d'ingrédients est valide (on ne devrait jamais lancer cette exception)
            if (qtyInventaire < qtyRecette) {
                throw new IngredientException("Ingrédients manquants dans l'inventaire");
            }

            // Actualise la quantité à l'inventaire
            ingredientCongelateur.setQty(qtyInventaire - qtyRecette);
        }
    }
}
