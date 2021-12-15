package Tentative2.Options;

import Tentative2.Produit;

public abstract class Option extends Produit {
    protected Produit produit;

    public Option() {
        produit = null;
    }

    public Option(Produit produit) {
        this.produit = produit;
    }
}
