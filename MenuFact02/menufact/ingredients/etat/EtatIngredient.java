package menufact.ingredients.etat;

import menufact.ingredients.Ingredient;
import menufact.ingredients.exceptions.IngredientException;

/**
 * Représente l'état physique d'un ingrédient (solide, liquide, gazeux, plasma, etc).
 * Contient la quantité pour pouvoir adapter entre des quantités en litres, en kg ou autres unités.
 */
public interface EtatIngredient {
    /**
     * Récupère la quantité d'un ingrédient
     * @return Quantité de l'ingrédient
     */
    public double getQty();

    /**
     * Modifie la quantité d'un ingrédient
     * @param qty Nouvelle quantité de l'ingrédient
     * @throws IngredientException Si la quantité est négative
     */
    public void setQty(double qty) throws IngredientException;

    /**
     * Permet de faire une deep-copy d'un état d'ingrédient
     *
     * @return Une copie de l'état d'ingrédient
     */
    public EtatIngredient makeCopy();

    /**
     * Vérifie si un état d'ingrédient est équivalent à un autre.
     *
     * @param other État à comparer
     * @return <code>true</code> si chaque attribut des états sont équivalents.
     */
    @Override
    public boolean equals(Object other);
}
