package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

/**
 * Factory pour créer différents types d'ingrédients.
 */
public interface CreatorIngredient {
    /**
     * Crée et retourne un nouvel ingrédient selon les paramètres passés.
     * @param nom   Nom de l'ingrédient à créer
     * @param etat  État physique de l'ingrédient à créer
     * @return      Nouvel ingrédient créé
     * @throws IngredientException  Si l'état est <code>null</code>
     */
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException;
}
