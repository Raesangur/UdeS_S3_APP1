package menufact.plats;

import menufact.plats.exception.PlatException;

public class PlatAuMenu {
    private int code;
    private String description;
    private double prix;
    private Recette recette;

    /**
     * Créateur du PlatAuMenu permetant d'être ajouter au menu
     * @param code int du code du plat dans le menu
     * @param description String description du plat
     * @param prix double pour le prix du plat
     * @throws PlatException Si on a une valeur négative
     */
    public PlatAuMenu(int code, String description, double prix) throws PlatException {
        this.code = code;
        this.description = description;
        if (prix >= 0) {
            this.prix = prix;
        } else {
            throw new PlatException("negative Number");
        }

    }

    /**
     * constructeur vide
     */
    public PlatAuMenu() {
    }

    /**
     * Permet d'afficher le plat sous forme de String avec ses informations.
     * @return String du plat avec ses infos
     */
    @Override
    public String toString() {
        return "menufact.plats.PlatAuMenu{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                "}\n";
    }

    /**
     * Donne le code du plat
     * @return le code du plat
     */
    public int getCode() {
        return code;
    }

    /**
     * Change le code du produit
     * @param code int du code du produit
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Donne la description du produit
     * @return la description du produit
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permet de changer la description du produit
     * @param description String pour la description du produit
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Donne la proportion d'un plat de base de 1
     * @return proportion de base de 1
     */
    public double getProportion() {
        return 1.0;
    }

    /**
     * Donne le prix du plat
     * @return Prix du plat
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Modifie le prix du plat
     * @param prix Nouveau prix du plat
     * @throws PlatException Si le nouveau prix est négatif
     */
    public void setPrix(double prix) throws PlatException {
        if (prix >= 0) {
            this.prix = prix;
        } else {
            throw new PlatException("negative Number");
        }

    }

    /**
     * Retourne la <code>Recette</code> du plat
     * @return Recette du plat
     */
    public Recette getRecette() {
        return recette;
    }

    /**
     * Modifie ou assigne une <code>Recette</code> au plat
     * @param recette        Recette représentant les ingrédients composant le plat
     * @throws PlatException Si la recette passée est <code>null</code>
     */
    public void setRecette(Recette recette) throws PlatException {
        if (recette == null) {
            throw new PlatException("Une recette ne peux pas être null");
        }
        this.recette = recette.makeCopy();
    }

    /**
     * Fait une deep-copy du plat
     * @return Une copie du plat.
     */
    public PlatAuMenu makeCopy() {
        try {
            PlatAuMenu pm = new PlatAuMenu(code, new String(description), prix);
            pm.setRecette(recette.makeCopy());
            return pm;
        } catch (PlatException pe) {
            pe.printStackTrace();
            return null;
        }
    }
}
