package CodeInitial.Options;

public class SiropErable implements Options {
    private String description;
    private double prix;
    private Options options;

    public SiropErable()
    {
        description = "SiropErable";
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
        return "SiropErable{" +
                "description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
