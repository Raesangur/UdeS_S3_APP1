package menufact.ingredients;

import menufact.ingredients.etat.EtatIngredient;
import menufact.ingredients.exceptions.IngredientException;

/**
 * Classe de base pour les différents types d'ingrédients, implémenté comme une classe et non une interface pour
 * permettre l'ajout d'attributs communs et de corps de méthodes communes.
 */
public abstract class Ingredient {
    protected EtatIngredient etat;
    private String nom;

    public Ingredient() {
    }

    /**
     * Permet de faire une deep-copy d'un ingrédient
     *
     * @return Une copie de l'ingrédient
     */
    public abstract Ingredient makeCopy();

    /**
     * Retourne le nom de l'ingrédient
     *
     * @return Nom de l'ingrédient
     */
    public String getNom() {
        return nom;
    }

    /**
     * Donne un nom à l'ingrédient. Si le nouveau nom est <code>null</code>, le nom n'est pas remplacé.
     *
     * @param nom Nouveau nom pour l'ingrédient
     */
    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;
        }
    }

    /**
     * Retourne la quantité de l'ingrédient.
     *
     * @return Quantité de l'ingrédient.
     */
    public double getQty() {
        return etat.getQty();
    }

    /**
     * Modifie la quantité de l'ingrédient.
     *
     * @param qty Nouvelle quantité de l'ingrédient.
     * @throws IngredientException Si la quantité indiquée est négative
     */
    public void setQty(double qty) throws IngredientException {
        etat.setQty(qty);
    }

    /**
     * Retourne l'état physique de l'ingrédient.
     *
     * @return État physique de l'ingrédient.
     */
    public EtatIngredient getEtat() {
        return etat;
    }

    /**
     * Vérifie si un ingrédient est équivalent à un autre.
     *
     * @param other Ingrédient à comparer
     * @return <code>true</code> si chaque attribut des ingrédients sont équivalents.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Ingredient) {
            return etat.equals(((Ingredient) other).getEtat()) && nom.equals(((Ingredient) other).getNom());
        } else {
            return false;
        }
    }
}
