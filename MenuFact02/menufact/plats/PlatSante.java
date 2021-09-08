package menufact.plats;

import menufact.plats.exception.PlatException;

public class PlatSante extends PlatAuMenu {
    private double kcal;
    private double chol;
    private double gras;

    public PlatSante(int code, String description, double prix, double kcal, double chol, double gras) throws PlatException {
        super(code, description, prix);
        if (kcal >= 0) {
            this.kcal = kcal;
        } else {
            throw new PlatException("negative Number");
        }
        this.setChol(chol);
        this.setGras(gras);
    }

    public PlatSante() {
    }

    @Override
    public String toString() {
        return "menufact.plats.PlatSante{" +
                "kcal=" + kcal +
                ", chol=" + getChol() +
                ", gras=" + getGras() +
                "} " + super.toString();
    }


    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) throws PlatException {
        if (kcal >= 0) {
            this.kcal = kcal;
        } else {
            throw new PlatException("negative Number");
        }
    }

    public double getChol() {
        return chol;
    }

    public void setChol(double chol) throws PlatException {
        if (chol >= 0) {
            this.chol = chol;
        } else {
            throw new PlatException("negative Number");
        }
    }

    public double getGras() {
        return gras;
    }

    public void setGras(double gras) throws PlatException {
        if (gras >= 0) {
            this.gras = gras;
        } else {
            throw new PlatException("negative Number");
        }
    }

    @Override
    public PlatAuMenu makeCopy() {
        try {
            PlatSante pm = new PlatSante(getCode(), new String(getDescription()), getPrix(), getKcal(), getChol(), getGras());
            pm.setRecette(getRecette().makeCopy());
            return pm;
        } catch (PlatException pe) {
            pe.printStackTrace();
            return null;
        }
    }
}
