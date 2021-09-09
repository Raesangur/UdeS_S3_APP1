package menufact.ingredients.exceptions;

/**
 * Représente une exception ou un cas d'erreur lié aux ingrédients.
 * Utilisé lorsqu'on cherche à passer un ingrédient ou un état d'ingrédient <code>null</code>,
 * Utilisé lorsqu'on manque d'ingrédients dans l'inventaire ou si on cherche un ingrédient innexistant.
 * Utilisé si on essaye de mettre une quantité d'ingrédient négative.
 * Utilisé si on essaye de préparer une quantité de plat négative ou avec des proportions invalides.
 */
public class IngredientException extends Exception{
    public IngredientException(String message){
        super("IngredientException: " + message);
    }
}
