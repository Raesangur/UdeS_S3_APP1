package CodeInitial.CremeGlacee;

import CodeInitial.Options.Options;

public class Chocolat implements CremeGlacee {
    private String description;
    private double prix;
    private Options options;
    public Chocolat()
    {
        description = "Chocolat";
        prix = 1.05;
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
        return "Chocolat{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                ", options=" + options +
                '}';
    }
}
