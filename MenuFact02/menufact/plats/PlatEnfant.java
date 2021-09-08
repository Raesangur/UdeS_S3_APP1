package menufact.plats;

import menufact.plats.exception.PlatException;

public class PlatEnfant extends PlatAuMenu {
    private double proportion;

    public PlatEnfant() {
    }

    public PlatEnfant(int code, String description, double prix, double proportion) throws PlatException {
        super(code, description, prix);
        setProportion(proportion);
    }

    @Override
    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) throws PlatException {
        if (proportion < 0) {
            throw new PlatException("Impossible d'avoir une proportion négative");
        }
        if (proportion > 1) {
            throw new PlatException("La proportion doit rester < 1");
        }
        this.proportion = proportion;
    }

    @Override
    public String toString() {
        return "PlatEnfant{" +
                "proportion=" + proportion +
                "} " + super.toString();
    }

    @Override
    public PlatAuMenu makeCopy() {
        try {
            PlatEnfant pm = new PlatEnfant(getCode(), new String(getDescription()), getPrix(), getProportion());
            pm.setRecette(getRecette().makeCopy());
            return pm;
        } catch (PlatException pe) {
            pe.printStackTrace();
            return null;
        }
    }
}
