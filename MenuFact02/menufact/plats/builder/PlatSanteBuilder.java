package menufact.plats.builder;

import menufact.plats.PlatSante;
import menufact.plats.exception.PlatException;

public class PlatSanteBuilder extends PlatBuilder {
    /**
     * Constructeur de PlatSanteBuilder initialise un platSanté vide
     */
    public PlatSanteBuilder() {
        plat = new PlatSante();
    }

    /**
     * Permet de changer le Kcal d'un PlatSante
     * @param kcal double représentant les kilocalories du plats
     * @return Le PlatSanteBuilder avec les Kcal changé
     * @throws PlatException Throw si Kcal est négatif
     */
    public PlatSanteBuilder buildKCal(double kcal) throws PlatException {
        ((PlatSante) plat).setKcal(kcal);
        return this;
    }

    /**
     * Permet de changer les Chol du PlatSante
     * @param chol du plat santé sous forme de double
     * @return Le PlatSanteBuilder avec les chol changé
     * @throws PlatException Throw si chol est négatif
     */
    public PlatSanteBuilder buildChol(double chol) throws PlatException {
        ((PlatSante) plat).setChol(chol);
        return this;
    }

    /**
     * Permet de changer les quantité de gras d'un PlatSanté
     * @param gras double représentant la quantité de gras du plat
     * @return Le PlatSanteBuilder avec les gras changé
     * @throws PlatException Throw si gras est négatif
     */
    public PlatSanteBuilder buildGras(double gras) throws PlatException {
        ((PlatSante) plat).setGras(gras);
        return this;
    }

    /**
     * Permet de vider le plat dans le builder
     * @return Le PlatSanteBuilder actuel avec un plat vide
     */
    @Override
    public PlatBuilder clear()
    {
        plat = new PlatSante();
        return this;
    }

    /**
     * Permet de retourner le plat final avec les valeurs changé
     * @return PlatSanté final avec ses informations changé.
     */
    @Override
    public PlatSante getResult() {
        return (PlatSante) plat;
    }
}
