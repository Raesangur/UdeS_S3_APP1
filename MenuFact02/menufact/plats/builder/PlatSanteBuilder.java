package menufact.plats.builder;

import menufact.plats.PlatSante;
import menufact.plats.exception.PlatException;

public class PlatSanteBuilder extends PlatBuilder {
    public PlatSanteBuilder() {
        plat = new PlatSante();
    }

    public PlatSanteBuilder buildKCal(double kcal) throws PlatException {
        ((PlatSante)plat).setKcal(kcal);
        return this;
    }
    public PlatSanteBuilder buildChol(double chol) throws PlatException {
        ((PlatSante)plat).setKcal(chol);
        return this;
    }
    public PlatSanteBuilder buildGras(double gras) throws PlatException {
        ((PlatSante)plat).setKcal(gras);
        return this;
    }
}
