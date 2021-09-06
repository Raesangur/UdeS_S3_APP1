package menufact;

import ingredients.exceptions.IngredientException;
import menufact.plats.PlatChoisi;
import menufact.plats.Recette;
import menufact.ingredients.Inventaire;
import menufact.ingredients.Ingredient;

public class Chef {
    private static Chef instance;
    private String nom;

    private Chef(){}
    public static synchronized Chef getInstance() {
        if (instance == null) {
            return instance = new Chef();
        }
        else {
            return instance;
        }
    }
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    // TODO: 2021-09-05
    public PlatChoisi cusiner(PlatChoisi platACuisiner) {
        return null;
    }
    private boolean verifierIngredient(PlatChoisi platAVerifier) throws IngredientException {
        Inventaire inventaire = Inventaire.getInstance();
        Recette recette = platAVerifier.getPlat().getRecette();

        for (Ingredient ingredient : recette.getIngredients()) {
            double qtyRecquise = platAVerifier.getQuantite() * ingredient.getQty();
            double qtyDisponible = inventaire.getIngredient(ingredient.getNom()).getQty();

            // Vérifie que l'ingrédient est disponible en quantité suffisante
            if (qtyDisponible < qtyRecquise) {
                // TODO : 2021-09-05 | met un etat d'erreur

                throw new IngredientException("Missing ingredient: " + ingredient.getNom());
            }
        }

        inventaire.consommerRecette(recette, platAVerifier.getQuantite());
        return true;
    }
}
