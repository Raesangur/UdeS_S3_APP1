package menufact.plats;

import menufact.plats.exception.PlatException;

public class PlatEnfant extends PlatAuMenu {
    private double proportion;

    public PlatEnfant() {
    }

    public PlatEnfant(int code, String description, double prix, double proportion) throws PlatException {
        super(code, description, prix);
        if(proportion >=0){
            this.proportion = proportion;
        }
        else{
            throw new PlatException("negative Number");
        }
    }

    @Override
    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) throws PlatException {
        if(proportion >=0){
            this.proportion = proportion;
        }
        else{
            throw new PlatException("negative Number");
        }
    }

    @Override
    public String toString() {
        return "PlatEnfant{" +
                "proportion=" + proportion +
                "} " + super.toString();
    }
}
