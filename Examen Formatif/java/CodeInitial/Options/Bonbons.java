package CodeInitial.Options;

public class Bonbons implements Options {
    private String description;
    private double prix;
    public Bonbons()
    {
        description = "Bonbons";
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
        return "Bonbons{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
