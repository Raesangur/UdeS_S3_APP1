package menufact.plats.builder;

import menufact.plats.PlatEnfant;
import menufact.plats.exception.PlatException;

public class PlatEnfantBuilder extends PlatBuilder {
    /**
     * Constructeur de PlatBuilder initialise un plat vide
     */
    public PlatEnfantBuilder() {
        plat = new PlatEnfant();
    }

    /**
     * Permet de changer la proportion de quantité du plat
     * @param proportion double représentant la proportion du plat que l'enfant veux manger.
     * @return Le PlatBuilder avec la proportion changer.
     * @throws PlatException Throw si la proportion est négative
     */
    public PlatEnfantBuilder buildProportion(double proportion) throws PlatException {
        ((PlatEnfant) plat).setProportion(proportion);
        return this;
    }

    /**
     * Permet de vider le plat avec un nouveau plat enfant vide
     * @return Le PlatBuilder avec le plat vide.
     */
    @Override
    public PlatBuilder clear()
    {
        plat = new PlatEnfant();
        return this;
    }

    /**
     * Donne le PlatEnfant final avec les informations créer
     * @return Le PlatEnfant avec ses informations complétés
     */
    @Override
    public PlatEnfant getResult() {
        return (PlatEnfant) plat;
    }
}
