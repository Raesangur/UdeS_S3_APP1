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

    /**
     * Permet de créer un chef ou de retourner le chef existant
     *
     * @return instance du chef
     */
    public static synchronized Chef getInstance() {
        return instance == null ? instance = new Chef() : instance;
    }

    /**
     * donne le nom du chef
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * change le nom du chef
     * @param nom string pour le nom du chef
     */
    public void setNom(String nom) {
        if(nom != null){
            this.nom = nom;
        }
    }

    /**
     * Appeler par l'observer de la facture et permet de cuisiner le platChoisi en changeant ses états de préparations en consommant les ingrédients dans l'inventaire en appelant ses autres fonctions
     * @param platACuisiner PlatChoisi qui va changer d'état et donner les ingrédients
     * @return PlatChoisi un fois servi ou null
     * @throws IngredientException S'il n'y a pas assez d'ingrédient pour le plat
     * @throws PlatException Si le plat ne peut pas être cuisiner error avec un changement d'état
     */
    public PlatChoisi cuisiner(PlatChoisi platACuisiner) throws IngredientException, PlatException {
        platACuisiner.setEtat(new Commande());

        if (verifierIngredient(platACuisiner)) {
            preparer(platACuisiner);
            terminer(platACuisiner);
            return servir(platACuisiner);
        } else {
            return null;
        }
    }

    /**
     * Vérifie la présence des Ingrédients dans l'inventaire pour permet la préparation de la recette
     * @param platAVerifier PlatChoisi qui donne les ingrédients et leur qty.
     * @return boolean vrai si valable faux sinon
     * @throws IngredientException return si les ingrédients ne sont pas possible
     * @throws PlatException si on une qty négative
     */
    private boolean verifierIngredient(PlatChoisi platAVerifier) throws IngredientException, PlatException {
        Inventaire inventaire = Inventaire.getInstance();
        Recette recette = platAVerifier.getPlat().getRecette();

        for (Ingredient ingredient : recette.getIngredients()) {
            double qtyRequise = platAVerifier.getQty() * platAVerifier.getPlat().getProportion() * ingredient.getQty();
            double qtyDisponible = inventaire.getIngredient(ingredient.getNom()).getQty();


            // Vérifie que l'ingrédient est disponible en quantité suffisante
            if (qtyDisponible < qtyRequise) {
                platAVerifier.setEtat(new ErrorServir());

                throw new IngredientException("Missing ingredient: " + ingredient.getNom());
            }
        }

        return true;
    }

    /**
     * Changer l'état du plat choisi vers préparer et consommer les ingrédients
     * @param platAPreparer PlatChoisi qui change d'état
     * @throws IngredientException return si les ingrédients ne sont pas possible
     * @throws PlatException Si le changement est imposible
     */
    private void preparer(PlatChoisi platAPreparer) throws IngredientException, PlatException {
        platAPreparer.setEtat(new EnPreparation());

        Inventaire inventaire = Inventaire.getInstance();
        Recette recette = platAPreparer.getPlat().getRecette();

        // Consomme les ingrédients dans l'inventaire
        inventaire.consommerRecette(recette, platAPreparer.getQty(), platAPreparer.getPlat().getProportion());
    }

    /**
     * change l'état du platChoisi pour terminé
     * @param platATerminer PlatChoisi qui change d'état
     * @throws PlatException Si le changement d'état est impossible
     */
    private void terminer(PlatChoisi platATerminer) throws PlatException{
        platATerminer.setEtat(new Termine());
    }

    /**
     * change l'état du platChoisi pour Servi
     * @param platAServir PlatChoisi qui change d'état
     * @return le plat à servir
     * @throws PlatException Changement d'état impossible
     */
    private PlatChoisi servir(PlatChoisi platAServir) throws PlatException {
        platAServir.setEtat(new Servi());
        return platAServir;
    }

    /**
     * Permet de représenter le chef sous forme de string
     * @return String du chef
     */
    @Override
    public String toString() {
        return "Chef: {\n\t" +
                "Nom: '" + nom + "'\n" +
                "\n}";
    }
}
