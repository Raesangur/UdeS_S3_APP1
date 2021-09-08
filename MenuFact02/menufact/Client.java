package menufact;

import menufact.facture.exceptions.FactureException;

import java.util.regex.*;

public class Client {
    private int idClient;
    private String nom;
    private String numeroCarteCredit;

    /**
     * Createur de client permetant de l'initialiser avec un nom, un id et un numéros de carte de crédit
     *
     * @param idClient          un int de l'id
     * @param nom               un sting pour le nom
     * @param numeroCarteCredit un string pour le numéro de carte de crédit qui doit être bien formaté
     * @throws FactureException si le numéro de carte de crédit n'est pas bon
     */
    public Client(int idClient, String nom, String numeroCarteCredit) throws FactureException {
        setIdClient(idClient);
        setNom(nom);
        setNumeroCarteCredit(numeroCarteCredit);
    }

    /**
     * donne le id du client
     *
     * @return le id du client
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * change le id du client
     *
     * @param idClient le id du client
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * Donne le nom du client
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Change nom du client
     *
     * @param nom le nom du client
     */
    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;
        }
    }

    /**
     * Donne le numéro de la carte de crédit
     *
     * @return numeroCarteCredit du client
     */
    public String getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    /**
     * Change le numéro de carte de crédit du client
     *
     * @param numeroCarteCredit le numéros de carte de crédit dans le bon format
     * @throws FactureException return si mauvais format ou null
     */
    public void setNumeroCarteCredit(String numeroCarteCredit) throws FactureException {
        if (numeroCarteCredit == null) {
            throw new FactureException("Numéro de carte de crédit ne peut pas être null");
        }
        // https://www.regextester.com/113011
        Pattern creditCardPattern = Pattern.compile("\\b(?:\\d{4}[ -]?){3}(?=\\d{4}\\b)(?:\\d{4})");
        Matcher creditCardPatternMatcher = creditCardPattern.matcher(numeroCarteCredit);
        if (creditCardPatternMatcher.matches()) {
            this.numeroCarteCredit = numeroCarteCredit;
        } else {
            throw new FactureException("Mauvais numéro de carte de crédit");
        }
    }

    /**
     * Permet de visualisé le client sous forme de String
     *
     * @return String du client
     */
    @Override
    public String toString() {
        return "menufact.Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", numeroCarteCredit='" + numeroCarteCredit + '\'' +
                '}';
    }
}