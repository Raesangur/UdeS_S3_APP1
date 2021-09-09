package menufact.ingredients.etat;

import menufact.ingredients.exceptions.IngredientException;

/**
 * Représente l'état d'un ingrédient sous forme gazeuse, telle que de la vapeur ou des sacs de chips Lays.
 */
public class EtatIngredientGazeux implements EtatIngredient {
    private double qtyL;

    /**
     * Construit l'état de l'ingrédient et configure sa quantité.
     *
     * @param qty Quantité de l'ingrédient en L
     * @throws IngredientException Si la quantité est négative.
     */
    public EtatIngredientGazeux(double qty) throws IngredientException {
        setQty(qty);
    }

    /**
     * Retourne la quantité de l'ingrédient en L
     *
     * @return Quantité de l'ingrédient
     */
    @Override
    public double getQty() {
        return qtyL;
    }

    /**
     * Modifie la quantité de l'ingrédient
     *
     * @param qty Nouvelle quantité de l'ingrédient (en L)
     * @throws IngredientException Si la quantité est négative
     */
    @Override
    public void setQty(double qty) throws IngredientException {
        if (qty < 0) {
            throw new IngredientException("Quantité d'ingrédient ne peut pas être négative");
        } else {
            qtyL = qty;
        }
    }

    /**
     * Permet de faire une deep-copy d'un état d'ingrédient gazeux
     *
     * @return Une copie de l'état d'ingrédient
     */
    @Override
    public EtatIngredient makeCopy() {
        try {
            return new EtatIngredientGazeux(getQty());
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
        return "'Gazeux': {\n\t 'Qty (L)': " + qtyL + "\n}";
    }

    /**
     * Vérifie si un l'état d'ingrédient gazeux est équivalent à un autre gaz.
     *
     * @param other État gazeux à comparer
     * @return <code>true</code> si chaque attribut des états sont équivalents.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof EtatIngredientGazeux) {
            return getQty() - ((EtatIngredient) other).getQty() < 0.05;
        } else {
            return false;
        }
    }
}

