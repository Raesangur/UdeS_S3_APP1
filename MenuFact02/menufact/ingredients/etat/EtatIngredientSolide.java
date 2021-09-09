package menufact.ingredients.etat;

import menufact.ingredients.exceptions.IngredientException;

/**
 * Représente l'état d'un ingrédient sous forme solide, telle que du fromage, une pomme ou de la viande.
 */
public class EtatIngredientSolide implements EtatIngredient {
    private double qtyKg;

    /**
     * Construit l'état de l'ingrédient et configure sa quantité.
     *
     * @param qty Quantité de l'ingrédient en kg
     * @throws IngredientException Si la quantité est négative.
     */
    public EtatIngredientSolide(double qty) throws IngredientException {
        setQty(qty);
    }

    /**
     * Retourne la quantité de l'ingrédient en kg
     *
     * @return Quantité de l'ingrédient
     */
    @Override
    public double getQty() {
        return qtyKg;
    }

    /**
     * Modifie la quantité de l'ingrédient
     *
     * @param qty Nouvelle quantité de l'ingrédient (en kg)
     * @throws IngredientException Si la quantité est négative
     */
    @Override
    public void setQty(double qty) throws IngredientException {
        if (qty < 0) {
            throw new IngredientException("Quantité d'ingrédient ne peut pas être négative");
        } else {
            qtyKg = qty;
        }
    }

    /**
     * Permet de faire une deep-copy d'un état d'ingrédient solide
     *
     * @return Une copie de l'état d'ingrédient
     */
    @Override
    public EtatIngredient makeCopy() {
        try {
            return new EtatIngredientSolide(getQty());
        } catch (IngredientException ie) {
            // Erreur impossible
            return null;
        }
    }

    /**
     * Convertit l'état d'ingrédient en une <code>String</code> sous représentation JSON.
     *
     * @return Une <code>String</code> représentant l'état d'ingrédient.
     */
    @Override
    public String toString() {
        return "'Solide': {\n\t 'Qty (kg)': " + qtyKg + "\n}";

    }

    /**
     * Vérifie si un l'état d'ingrédient solide est équivalent à un autre solide.
     *
     * @param other État solide à comparer
     * @return <code>true</code> si chaque attribut des états sont équivalents.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof EtatIngredientSolide) {
            return getQty() - ((EtatIngredient) other).getQty() < 0.05;
        } else {
            return false;
        }
    }
}
