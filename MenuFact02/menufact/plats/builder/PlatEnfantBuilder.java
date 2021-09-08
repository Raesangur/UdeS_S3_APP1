package menufact.plats.builder;

import menufact.plats.PlatAuMenu;
import menufact.plats.PlatEnfant;
import menufact.plats.exception.PlatException;

public class PlatEnfantBuilder extends PlatBuilder {
    public PlatEnfantBuilder() {
        plat = new PlatEnfant();
    }


    public PlatEnfantBuilder buildProportion(double proportion) throws PlatException {
        ((PlatEnfant) plat).setProportion(proportion);
        return this;
    }

    @Override
    public PlatBuilder clear()
    {
        plat = new PlatEnfant();
        return this;
    }

    @Override
    public PlatEnfant getResult() {
        return (PlatEnfant) plat;
    }
}
