package menufact;

import menufact.ingredients.Ingredient;
import menufact.ingredients.Inventaire;
import menufact.ingredients.exceptions.IngredientException;
import menufact.plats.PlatChoisi;
import menufact.plats.Recette;
import menufact.plats.exception.PlatException;
import menufact.plats.state.*;

public class Chef {
    private static Chef instance;
    private String nom;

    private Chef() {
    }

    public static synchronized Chef getInstance() {
        return instance == null ? new Chef() : instance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public PlatChoisi cusiner(PlatChoisi platACuisiner) throws IngredientException, PlatException {
        platACuisiner.setEtat(new Commande());

        if (verifierIngredient(platACuisiner)) {
            preparer(platACuisiner);
            terminer(platACuisiner);
            return servir(platACuisiner);
        } else {
            return null;
        }
    }

    private boolean verifierIngredient(PlatChoisi platAVerifier) throws IngredientException, PlatException {
        Inventaire inventaire = Inventaire.getInstance();
        Recette recette = platAVerifier.getPlat().getRecette();

        for (Ingredient ingredient : recette.getIngredients()) {
            double qtyRecquise = platAVerifier.getQty() * platAVerifier.getPlat().getProportion() * ingredient.getQty();
            double qtyDisponible = inventaire.getIngredient(ingredient.getNom()).getQty();


            // Vérifie que l'ingrédient est disponible en quantité suffisante
            if (qtyDisponible < qtyRecquise) {
                platAVerifier.setEtat(new ErrorServir());

                throw new IngredientException("Missing ingredient: " + ingredient.getNom());
            }
        }

        return true;
    }

    private void preparer(PlatChoisi platAPreparer) throws IngredientException, PlatException {
        platAPreparer.setEtat(new EnPreparation());

        Inventaire inventaire = Inventaire.getInstance();
        Recette recette = platAPreparer.getPlat().getRecette();

        // Consomme les ingrédients dans l'inventaire
        inventaire.consommerRecette(recette, platAPreparer.getQty(), platAPreparer.getPlat().getProportion());
    }

    private void terminer(PlatChoisi platATerminer) throws PlatException{
        platATerminer.setEtat(new Termine());
    }

    private PlatChoisi servir(PlatChoisi platAServir) throws PlatException {
        platAServir.setEtat(new Servi());
        return platAServir;
    }

    @Override
    public String toString() {
        return "Chef: {\n\t" +
                "Nom: '" + nom + "'\n" +
                "\n}";
    }
}
