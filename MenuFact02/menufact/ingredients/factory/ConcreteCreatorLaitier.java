package menufact.ingredients.factory;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Laitier;
import menufact.ingredients.exceptions.IngredientException;

public class ConcreteCreatorLaitier implements CreatorIngredient {
    /**
     * Crée et retourne un nouveau produit <code>Laitier</code> selon les paramètres passés.
     *
     * @param nom  Nom de l'ingrédient à créer
     * @param etat État physique de l'ingrédient à créer
     * @return Nouvel ingrédient créé
     * @throws IngredientException Si l'état est <code>null</code>
     */
    @Override
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Laitier(nom, etat);
    }
}
