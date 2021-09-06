package menufact;

import ingredients.exceptions.IngredientException;
import menufact.plats.PlatChoisi;
import menufact.plats.Recette;
import menufact.ingredients.Inventaire;
import menufact.ingredients.Ingredient;
import menufact.plats.state.Commande;
import menufact.plats.state.EnPreparation;
import menufact.plats.state.Termine;
import menufact.plats.state.Servi;

public class Chef {
    private static Chef instance;
    private String nom;

    private Chef() {
    }

    public static synchronized Chef getInstance() {
        if (instance == null) {
            return instance = new Chef();
        } else {
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
    public PlatChoisi cusiner(PlatChoisi platACuisiner) throws IngredientException {
        platACuisiner.setEtat(new Commande());

        if (verifierIngredient(platACuisiner)) {
            preparer(platACuisiner);
            terminer(platACuisiner);
            return servir(platACuisiner);
        } else {

            return null;
        }
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

        return true;
    }

    private void preparer(PlatChoisi platAPreparer) throws IngredientException {
        platAPreparer.setEtat(new EnPreparation());

        Inventaire inventaire = Inventaire.getInstance();
        Recette recette = platAPreparer.getPlat().getRecette();

        // Consomme les ingrédients dans l'inventaire
        inventaire.consommerRecette(recette, platAPreparer.getQuantite());
    }

    private void terminer(PlatChoisi platATerminer) {
        platATerminer.setEtat(new Termine());
    }

    private PlatChoisi servir(PlatChoisi platAServir) {
        platAServir.setEtat(new Servi());
        return platAServir;
    }
}
