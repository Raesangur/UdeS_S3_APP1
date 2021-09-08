package menufact.ingredients;

import menufact.ingredients.exceptions.IngredientException;
import menufact.plats.Recette;
import menufact.plats.builder.PlatBuilder;

import java.util.HashMap;

/**
 * Classe Singleton qui contient les différents ingrédients en stock dans le restaurant, dans une <code>HashMap</code>.
 * Cette classe peut ajouter des nouveaux ingrédients et actualiser leur quantités.
 */
public class Inventaire {
    private static Inventaire instance;
    private HashMap<String, Ingredient> congelateur;

    /**
     * Permet l'accès au Singleton de l'inventaire.
     * Si l'inventaire n'est pas encore créé, crée une instance de l'inventaire, sinon retourne l'instance déjà existante.
     *
     * @return Instance unique de l'inventaire
     */
    public static synchronized Inventaire getInstance() {
        return instance == null ? instance = new Inventaire() : instance;
    }

    /**
     * Constructeur privé pour éviter la construction d'objets inventaires en dehors du contrôle
     * de la méthode <code>getInstance</code>.
     */
    private Inventaire() {
        congelateur = new HashMap<>();
    }

    /**
     * Ajoute un tableau d'ingrédients à l'inventaire.
     * Appelle la méthode <code>ajouterIngredient</code> sur chaque objet du tableau.
     *
     * @param ingredients Tableau d'ingrédients à rajouter à l'inventaire.
     * @throws IngredientException Si un ingrédient ou le tableau d'ingrédient est null.
     */
    public void ajouterIngredient(Ingredient[] ingredients) throws IngredientException {
        if (ingredients == null) {
            throw new IngredientException("Impossible de rajouter un ingrédient null à l'inventaire");
        }
        // Si l'ingrédient est déjà dans l'inventaire, ajoute la quantité à l'ingrédient existant
        for (Ingredient ingredient : ingredients) {
            ajouterIngredient(ingredient);
        }
    }

    /**
     * Ajoute un ingrédient à l'inventaire.
     * Si l'ingrédient est déjà dans l'inventaire, additionne les quantités.
     *
     * @param ingredient Ingrédient à rajouter à l'inventaire.
     * @throws IngredientException Si l'ingrédient est null.
     */
    public void ajouterIngredient(Ingredient ingredient) throws IngredientException {
        if (ingredient == null) {
            throw new IngredientException("Impossible de rajouter un ingrédient null à l'inventaire");
        } else {
            // Si l'ingrédient est déjà dans l'inventaire, ajoute la quantité à l'ingrédient existant
            if (congelateur.containsKey(ingredient.getNom())) {
                Ingredient ing = congelateur.get(ingredient.getNom());
                ing.setQty(ing.getQty() + ingredient.getQty());
            } else {
                congelateur.put(ingredient.getNom(), ingredient.makeCopy());
            }
        }
    }

    /**
     * Récupère un ingrédient dans l'inventaire par son nom.
     *
     * @param nomIngredient Nom de l'ingrédient à chercher
     * @return Ingrédient recherché,
     * ou <code>null</code> si l'ingrédient ne fait pas partie de l'inventaire.
     */
    public Ingredient getIngredient(String nomIngredient) {
        return congelateur.get(nomIngredient);
    }

    /**
     * Récupère la quantité d'ingrédients différents dans l'inventaire.
     *
     * @return Quantité d'ingrédients différents dans l'inventaire.
     */
    public int getIngredientSize() {
        return congelateur.size();
    }

    /**
     * Consomme les ingrédients d'une recette dans l'inventaire.
     *
     * @param recette    Recette à consommer
     * @param qtyPlats   Nombre de plats à consommer
     * @param proportion Proportion de la recette à consommer
     * @throws IngredientException Si la recette est <code>null</code>
     * @throws IngredientException Si la quantité de plats est négative.
     * @throws IngredientException Si la proportion de la recette est négative.
     * @throws IngredientException Si la proportion de la recette est plus grande que 1.
     * @throws IngredientException Si un ingrédient de la recette n'est pas dans l'inventaire.
     * @throws IngredientException Si on manque d'ingrédients à l'inventaire.
     */
    public void consommerRecette(Recette recette, int qtyPlats, double proportion) throws IngredientException {
        // Guards
        if (recette == null) {
            throw new IngredientException("Recette ne peut pas être null");
        }
        if (qtyPlats < 0) {
            throw new IngredientException("Impossible de consommer une quantité négative de plats");
        }
        if (proportion < 0) {
            throw new IngredientException("Impossible de consommer des plats à proportion négative");
        }
        if (proportion > 1) {
            throw new IngredientException("Proportion de la recette ne peut pas être > 1");
        }

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

    /**
     * Vide l'inventaire
     */
    public static void clear() {
        if (instance != null) {
            instance.congelateur.clear();
            instance = null;
        }
    }

    /**
     * Converti le contenu de l'inventaire en <code>String</code>.
     *
     * @return Une <code>String</code> qui représente l'inventaire.
     */
    @Override
    public String toString() {
        return "Inventaire: " + congelateur;
    }
}
