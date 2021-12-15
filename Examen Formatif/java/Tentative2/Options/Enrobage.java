package Tentative2.Options;
import Tentative2.Produit;

public class Enrobage extends Option {
    public Enrobage() {
        description = "Enrobage";
        prix = 0.3;
    }

    public Enrobage(Produit produit) {
        super(produit);
        description = produit.getDescription() + " + Enrobage";
        prix = produit.getPrix() + 0.3;
    }

    @Override
    public String toString() {
        return "Enrobage{" +
                "description='" + getDescription() + '\'' +
                ", prix=" + getPrix() +
                '}';
    }
}
