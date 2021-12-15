package Tentative.Options;

import Tentative.CremeGlacee.CremeGlacee;

public class Options extends CremeGlacee{
    private CremeGlacee cremeGlacee;

    public String getDescription() {
        return cremeGlacee.getDescription() + " + " + description;
    }

    public double cost() {
        return cremeGlacee.cost() + prix;
    }

    @Override
    public String toString() {
        return "{" + "description='" + getDescription() + '\'' + ", prix=" + prix + '}';
    }
}
