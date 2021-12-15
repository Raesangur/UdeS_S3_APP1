package SolutionFormatif.CremeGlacee;

import SolutionFormatif.Produit;

public class Napolitain implements Produit {
    private String description;
    private double prix;

    public Napolitain()
    {
        //les lignes suivantes definissent les valeurs de la classe
        description = "Napolitain";
        prix = 2.55;
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
        return "Napolitain{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
