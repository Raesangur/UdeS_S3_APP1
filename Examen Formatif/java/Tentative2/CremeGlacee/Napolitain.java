package Tentative2.CremeGlacee;

import Tentative2.Options.Option;

public class Napolitain extends CremeGlacee {

    public Napolitain()
    {
        description = "Napolitain";
        prix = 2.55;
    }

    @Override
    public String toString() {
        return "Napolitain{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
