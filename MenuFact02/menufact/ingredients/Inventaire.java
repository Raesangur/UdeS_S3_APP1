package menufact.ingredients;

import java.util.HashMap;

import menufact.ingredients.exceptions.IngredientException;
import menufact.plats.Recette;

public class Inventaire {
    private static Inventaire instance;
    private HashMap<String, Ingredient> congelateur;

    private Inventaire() {}

    public static synchronized Inventaire getInstance() {
        if (instance == null) {
            return instance = new Inventaire();
        }
        return instance;
    }

    public void ajouterIngredient(Ingredient ingredient) {
        // Si l'ingrédient est déjà dans l'inventaire, ajoute la quantité à l'ingrédient existant
        if (congelateur.containsKey(ingredient.getNom())) {
            Ingredient ing = congelateur.get(ingredient.getNom());
            ing.setQty(ing.getQty() + ingredient.getQty());
        }
        else {
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
