package menufact.plats.builder;

import menufact.plats.PlatSante;

public class PlatSanteBuilder extends PlatBuilder {
    public PlatSanteBuilder() {
        plat = new PlatSante();
    }

    public PlatSanteBuilder buildKCal(double kcal) {
        ((PlatSante)plat).setKcal(kcal);
        return this;
    }
    public PlatSanteBuilder buildChol(double chol) {
        ((PlatSante)plat).setKcal(chol);
        return this;
    }
    public PlatSanteBuilder buildGras(double gras) {
        ((PlatSante)plat).setKcal(gras);
        return this;
    }
}
