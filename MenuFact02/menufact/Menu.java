package menufact;

import menufact.exceptions.MenuException;
import menufact.plats.PlatAuMenu;

import java.util.ArrayList;

public class Menu {
    private static Menu instance;
    private String description;
    private int courant;
    private ArrayList<PlatAuMenu> plat = new ArrayList<PlatAuMenu>();

    /**
     * Créer un menu contenant sa description
     *
     * @param description un string de description
     */
    private Menu(String description) {
        this.description = description;
    }

    /**
     * refere ou créer un menu s'il existe déjà un menu
     *
     * @return instance du menu
     */
    public synchronized static Menu getInstance() {
        return instance == null ? instance = new Menu(null) : instance;
    }

    /**
     * Donne la description du menu
     *
     * @return description du menu
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permet de Changer la description du menu
     *
     * @param description un string de description
     */
    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    /**
     * Permet d'ajouter un Plat au menu sous forme de PlatAuMenu
     *
     * @param p un PlatAuMenu à ajouter
     */
    public void ajoute(PlatAuMenu p) {
        if (p != null) {
            plat.add(p);
        }
    }

    /**
     * Permet de changer la position actuel du menu
     *
     * @param i un int représentant la position
     */
    public void position(int i) {
        courant = i;
    }

    /**
     * donne le plat a la position courante du menu
     *
     * @return Plat courant
     */
    public PlatAuMenu platCourant() {
        return plat.get(courant);
    }

    /**
     * Change la position vers la position suivante
     *
     * @throws MenuException si le courant+1 est plus grand que le size du plat on le return
     */
    public void positionSuivante() throws MenuException {
        if (courant + 1 >= plat.size())
            throw new MenuException("On depasse le nombre maximale de plats.");
        else
            courant++;
    }

    /**
     * Change la position courante pour la position précédente
     *
     * @throws MenuException si le courant-1 est plus petit que 0 on le return
     */
    public void positionPrecedente() throws MenuException {
        if (courant - 1 < 0)
            throw new MenuException("On depasse le nombre minimale de plats");
        else
            courant--;
    }

    /**
     * Permet d'afficher le menu sous forme de String
     *
     * @return String du Menu
     */

    @Override
    public String toString() {
        return "menufact.Menu{" +
                "description='" + description + '\'' +
                ", courant=" + courant +
                ", plat=" + "\n" + plat +
                '}';
    }
}
