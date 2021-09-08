package menufact;

import menufact.facture.exceptions.FactureException;

import java.util.regex.*;

public class Client {
    private int idClient;
    private String nom;
    private String numeroCarteCredit;

    public Client(int idClient, String nom, String numeroCarteCredit) throws FactureException {
        setIdClient(idClient);
        setNom(nom);
        setNumeroCarteCredit(numeroCarteCredit);
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom != null) {
            this.nom = nom;
        }
    }

    public String getNumeroCarteCredit() {
        return numeroCarteCredit;
    }

    public void setNumeroCarteCredit(String numeroCarteCredit) throws FactureException {
        // https://stackoverflow.com/a/9315696/10827197
        Pattern creditCardPattern = Pattern.compile("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$");
        Matcher creditCardPatternMatcher = creditCardPattern.matcher(numeroCarteCredit);
        if (creditCardPatternMatcher.matches()) {
            this.numeroCarteCredit = numeroCarteCredit;
        } else {
            throw new FactureException("Mauvais numéro de carte de crédit");
        }
    }

    @Override
    public String toString() {
        return "menufact.Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", numeroCarteCredit='" + numeroCarteCredit + '\'' +
                '}';
    }
}