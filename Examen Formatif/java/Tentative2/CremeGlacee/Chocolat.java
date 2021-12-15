package Tentative2.CremeGlacee;

import Tentative2.Options.Option;

public class Chocolat extends CremeGlacee {
    public Chocolat()
    {
        description = "Chocolat";
        prix = 1.05;
    }

    @Override
    public String toString() {
        return "Chocolat{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
