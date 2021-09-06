package menufact.plats.builder;

import menufact.plats.PlatEnfant;

public class PlatEnfantBuilder extends PlatBuilder {
    public PlatEnfantBuilder() {
        plat = new PlatEnfant();
    }

    public PlatEnfantBuilder buildProportion(double proportion) {
        ((PlatEnfant)plat).setProportion(proportion);
        return this;
    }
}
