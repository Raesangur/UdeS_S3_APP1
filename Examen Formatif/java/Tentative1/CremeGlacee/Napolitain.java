package Tentative.CremeGlacee;

public class Napolitain implements CremeGlacee {
    private String description;
    private double prix;
    private Options options;

    public Napolitain()
    {
        description = "Napolitain";
        prix = 2.55;
    }
    public String getDescription()
    {
        return description;
    }
    public double cost(){
        return prix;
    }

    @Override
    public String toString() {
        return "Napolitain{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                ", options=" + options +
                '}';
    }
}
