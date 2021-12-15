package Tentative.CremeGlacee;

public abstract class CremeGlacee {
    protected String description;
    protected double prix;

    public String getDescription()
    {
        return description;
    }
    public double cost(){
        return prix;
    }

    @Override
    public String toString() {
        return "{" + "description='" + description + '\'' + ", prix=" + prix + '}';
    }
}
