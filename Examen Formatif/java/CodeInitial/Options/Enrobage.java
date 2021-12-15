package CodeInitial.Options;

public class Enrobage implements Options {
    private String description;
    private double prix;
    public Enrobage()
    {
        description = "Enrobage";
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
        return "Enrobage{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
