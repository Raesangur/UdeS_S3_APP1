package menufact.ingredients.factory;

import menufact.ingredients.Epice;
import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

public class ConcreteCreatorEpice implements CreatorIngredient {

    /**
     * Méthode de la factory qui crée un nouveau <code>Epice</code> à partir des paramètres passés.
     *
     * @param nom  Nom de l'ingrédient à créer
     * @param etat État physique de l'ingrédient à créer
     * @return Nouveau fruit créé
     * @throws IngredientException Si l'état d'ingrédient est <code>null</code>
     */
    @Override
    public Ingredient creer(String nom, EtatIngredient etat) throws IngredientException {
        return new Epice(nom, etat);
    }
}
