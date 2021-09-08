package menufact.plats;

import menufact.plats.exception.PlatException;

public class PlatAuMenu {
    private int code;
    private String description;
    private double prix;
    private Recette recette;

    public PlatAuMenu(int code, String description, double prix) throws PlatException {
        this.code = code;
        this.description = description;
        if (prix >= 0) {
            this.prix = prix;
        } else {
            throw new PlatException("negative Number");
        }

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

    public void setPrix(double prix) throws PlatException {
        if (prix >= 0) {
            this.prix = prix;
        } else {
            throw new PlatException("negative Number");
        }

    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) throws PlatException {
        if (recette == null) {
            throw new PlatException("Une recette ne peux pas être null");
        }
        this.recette = recette.makeCopy();
    }

    public PlatAuMenu makeCopy() {
        try {
            PlatAuMenu pm = new PlatAuMenu(code, new String(description), prix);
            pm.setRecette(recette.makeCopy());
            return pm;
        } catch (PlatException pe) {
            pe.printStackTrace();
            return null;
        }
    }
}
