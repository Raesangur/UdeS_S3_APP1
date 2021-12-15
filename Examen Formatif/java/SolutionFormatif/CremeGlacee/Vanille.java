package SolutionFormatif.CremeGlacee;

import SolutionFormatif.Produit;

public class Vanille implements Produit {
    private String description;
    private double prix;

    public Vanille()
    {
        //les lignes suivantes definissent les valeurs de la classe
        description = "Vanille";
        prix = 1.55;
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
        return "Vanille{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
