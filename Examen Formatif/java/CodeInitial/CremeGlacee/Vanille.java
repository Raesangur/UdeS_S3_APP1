package CodeInitial.CremeGlacee;

import CodeInitial.Options.Options;

public class Vanille implements CremeGlacee {
    private String description;
    private double prix;
    private Options options;

    public Vanille()
    {
        description = "Vanille";
        prix = 1.55;
    }
    public String getDescription()
    {
        return description;
    }
    public double cost(){
        return prix;
    }

    public void addOption(Options options){
        this.options = options;
        this.prix = this.prix + options.cost();
    }

    @Override
    public String toString() {
        return "Vanille{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                ", options=" + options +
                '}';
    }
}
