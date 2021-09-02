package codeInitial;

public class Professeur {

    // Singleton implementation
    private static Professeur instance;

    private String cip;
    private String nom;

    // Private constructor to avoid instantiation outside of the class
    private Professeur(){}

    // Accessing the object
    public static synchronized Professeur getInstance() {
        if (instance == null) {
            instance = new Professeur();
        }

        return instance;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }
    public String getCip() {
        return this.cip;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return this.nom;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "hash code=" + this.hashCode() + " \n"+
                "cip='" + this.cip + '\'' +
                ", nom='" + this.nom + '\'' +
                '}';
    }
}
