package menufact.plats;

public class PlatAuMenu {
    private int code;
    private String description;
    private double prix;
    private Recette recette;

    public PlatAuMenu(int code, String description, double prix) {
        this.code = code;
        this.description = description;
        this.prix = prix;
    }

    public PlatAuMenu() {
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatAuMenu{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                "}\n";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProportion() {
        return 1.0;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }
}
