package Tentative2.Options;

import Tentative2.Produit;

public class Bonbons extends Option {
    public Bonbons() {
        description = "Bonbons";
        prix = 0.5;
    }

    public Bonbons(Produit produit) {
        super(produit);
        description = produit.getDescription() + " + Bonbons";
        prix = produit.getPrix() + 0.5;
    }
    @Override
    public String toString() {
        return "Bonbons{" +
                "description='" + getDescription() + '\'' +
                ", prix=" + getPrix() +
                '}';
    }
}
