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
        if(numeroCarteCredit != null){
            // https://stackoverflow.com/a/9315696/10827197
            Pattern creditCardPattern = Pattern.compile("\\b(?:\\d{4}[ -]?){3}(?=\\d{4}\\b)(?:\\d{4})");
            Matcher creditCardPatternMatcher = creditCardPattern.matcher(numeroCarteCredit);
            if (creditCardPatternMatcher.matches()) {
                this.numeroCarteCredit = numeroCarteCredit;
            } else {
                throw new FactureException("Mauvais numéro de carte de crédit");
            }
        }
        else{
            throw new FactureException("Numéros de carte de Crédit null");
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