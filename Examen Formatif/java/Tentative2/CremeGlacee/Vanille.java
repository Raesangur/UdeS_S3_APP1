package Tentative2.CremeGlacee;

import Tentative2.Options.Option;

public class Vanille extends CremeGlacee {

    public Vanille()
    {
        description = "Vanille";
        prix = 1.55;
    }
    @Override
    public String toString() {
        return "Vanille{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
