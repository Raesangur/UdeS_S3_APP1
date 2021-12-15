package SolutionFormatif.CremeGlacee;

import SolutionFormatif.Produit;

public class Chocolat implements Produit {
    private String description;
    private double prix;

    public Chocolat()
    {
        //les lignes suivantes definissent les valeurs de la classe
        description = "Chocolat";
        prix = 1.05;
    }

    /*
     * cette fonction permet d'avoir acces aux variables privees de la classe
     */
    public String getDescription()
    {
        return description;
    }

    /*
     * cette fonction permet d'avoir acces aux variables privees de la classe
     */
    public double cost()
    {
        return prix;
    }

    @Override
    public String toString()
    {
        return "Chocolat{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
