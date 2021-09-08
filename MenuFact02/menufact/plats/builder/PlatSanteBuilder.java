package menufact.plats.builder;

import menufact.plats.PlatEnfant;
import menufact.plats.PlatSante;
import menufact.plats.exception.PlatException;

public class PlatSanteBuilder extends PlatBuilder {
    public PlatSanteBuilder() {
        plat = new PlatSante();
    }

    public PlatSanteBuilder buildKCal(double kcal) throws PlatException {
        ((PlatSante) plat).setKcal(kcal);
        return this;
    }

    public PlatSanteBuilder buildChol(double chol) throws PlatException {
        ((PlatSante) plat).setChol(chol);
        return this;
    }

    public PlatSanteBuilder buildGras(double gras) throws PlatException {
        ((PlatSante) plat).setGras(gras);
        return this;
    }

    @Override
    public PlatBuilder clear()
    {
        plat = new PlatSante();
        return this;
    }

    @Override
    public PlatSante getResult() {
        return (PlatSante) plat;
    }
}
