package menufact.facture;

import menufact.Chef;
import menufact.Client;
import menufact.facture.exceptions.FactureException;
import menufact.facture.state.FactureEtat;
import menufact.facture.state.FactureEtatFermee;
import menufact.facture.state.FactureEtatOuverte;
import menufact.facture.state.FactureEtatPayee;
import menufact.ingredients.exceptions.IngredientException;
import menufact.plats.PlatChoisi;
import menufact.plats.exception.PlatException;

import java.util.ArrayList;
import java.util.Date;

/**
 * Une facture du systeme Menufact
 *
 * @author Domingo Palao Munoz
 * @version 1.0
 */
public class Facture {
    private Date date;
    private String description;
    private FactureEtat etat;
    private ArrayList<PlatChoisi> platchoisi = new ArrayList<PlatChoisi>();
    private int courant;
    private Client client;
    private Chef chef;


    /**********************Constantes ************/
    private final double TPS = 0.05;
    private final double TVQ = 0.095;

    /**
     * @param client le client de la facture
     */
    public void associerClient(Client client) {
        this.client = client;
    }

    /**
     * @param chef le chef de la facture
     */
    public void associerChef(Chef chef) {
        this.chef = chef;
    }

    /**
     * Calcul du sous total de la facture
     *
     * @return le sous total
     */
    public double sousTotal() {
        double soustotal = 0;
        for (PlatChoisi p : platchoisi)
            soustotal += p.getQty() * p.getPlat().getPrix();
        return soustotal;
    }

    /**
     * @return le total de la facture
     */
    public double total() {
        return sousTotal() + tps() + tvq();
    }

    /**
     * @return la valeur de la TPS
     */
    private double tps() {
        return TPS * sousTotal();
    }

    /**
     * @return la valeur de la TVQ
     */
    private double tvq() {
        return TVQ * (TPS + 1) * sousTotal();
    }

    /**
     * Permet de chager l'état de la facture à PAYEE
     */
    public void payer() throws FactureException {
        if (etat.changerEtat(new FactureEtatPayee())) {
            etat = new FactureEtatPayee();
        } else {
            throw new FactureException("La facture ne peut pas être payée.");
        }
    }

    /**
     * Permet de changer l'état de la facture à FERMEE
     */
    public void fermer() throws FactureException {
        if (etat.changerEtat(new FactureEtatFermee())) {
            etat = new FactureEtatFermee();
        } else {
            throw new FactureException("La facture ne peut pas être fermée.");
        }
    }

    /**
     * Permet de changer l'état de la facture à OUVERTE
     *
     * @throws FactureException en cas que la facture soit PAYEE
     */
    public void ouvrir() throws FactureException {
        if (etat.changerEtat(new FactureEtatOuverte())) {
            etat = new FactureEtatOuverte();
        } else {
            throw new FactureException("La facture ne peut pas être reouverte.");
        }
    }

    /**
     * @return l'état de la facture
     */
    public FactureEtat getEtat() {
        return etat;
    }

    /**
     * @param description la description de la Facture
     */
    public Facture(String description) {
        date = new Date();
        etat = new FactureEtatOuverte();
        courant = -1;
        this.description = description;
    }

    /**
     * @param p un plat choisi
     * @throws FactureException Seulement si la facture est OUVERTE
     */
    public void ajoutePlat(PlatChoisi p) throws FactureException, PlatException {
        if (etat instanceof FactureEtatOuverte) {
            if (p == null) {
                throw new PlatException("Impossible de rajoputer un plat null à la facture");
            }

            if (chef == null) {
                throw new FactureException("Il n'y a pas de chef.");
            } else {
                try {
                    chef.cuisiner(p);
                    platchoisi.add(p);
                } catch (IngredientException ie) {
                    System.out.println("Il n'y a pas assez d'ingrédient" + ie.getMessage());
                }
            }
        } else {
            throw new FactureException("On peut ajouter un plat seulement sur une facture OUVERTE.");
        }
    }


    /**
     * @return le contenu de la facture en chaîne de caracteres
     */
    @Override
    public String toString() {
        return "menufact.facture.Facture{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", etat=" + etat +
                ", platchoisi=" + platchoisi +
                ", courant=" + courant +
                ", client=" + client +
                ", TPS=" + TPS +
                ", TVQ=" + TVQ +
                '}';
    }

    /**
     * @return une chaîne de caractères avec la facture à imprimer
     */
    public String genererFacture() {
        String lesPlats = "";
        int i = 1;

        String factureGenere = "Facture generee.\n" +
                "Date:" + date + "\n" +
                "Description: " + description + "\n" +
                "Client:" + client.getNom() + "\n" +
                "Les plats commandes:" + "\n" + lesPlats;

        factureGenere += "Seq   Plat         Prix   Quantite\n";
        for (PlatChoisi plat : platchoisi) {
            factureGenere += i + "     " + plat.getPlat().getDescription() + "  " + plat.getPlat().getPrix() + "      " + plat.getQty() + "\n";
            i++;
        }

        factureGenere += "          TPS:               " + tps() + "\n";
        factureGenere += "          TVQ:               " + tvq() + "\n";
        factureGenere += "          Le total est de:   " + total() + "\n";

        return factureGenere;
    }

    public void Subscribe(Chef cook) {
        chef = cook;
    }
}
