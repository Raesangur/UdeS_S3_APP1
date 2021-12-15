package Tentative2.Options;
import Tentative2.Produit;

public class SiropErable extends Option {
    private Option options;

    public SiropErable() {
        description = "Bonbons";
        prix = 0.75;
    }

    public SiropErable(Produit produit) {
        super(produit);
        description = produit.getDescription() + " + Bonbons";
        prix = produit.getPrix() + 0.75;
    }

    @Override
    public String toString() {
        return "SiropErable{" +
                "description='" + getDescription() + '\'' +
                ", prix=" + getPrix() +
                '}';
    }
}
