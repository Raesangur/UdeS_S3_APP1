package SolutionFormatif.Options;

import SolutionFormatif.Produit;

/*
 * classe reponsable de la gestion des decorations
 */

/*
 * on implements l'interface Produit pour definir
 * le corps des fonctions virtuelles et ainsi pouvoir
 * ajouter des fonctionalités a ces fonctions
 */
public class Options implements Produit {
    protected Produit produit;

    public Options(Produit produit)
    {
        this.produit = produit;
    }

    /*
     * retourne la description de la variable produit
     */
    @Override
    public String getDescription()
    {
        return produit.getDescription();
    }

    /*
     * retourne le cout de la variable produit
     */
    @Override
    public double cost()
    {
        return produit.cost();
    }
}
