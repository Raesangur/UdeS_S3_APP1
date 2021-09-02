package codeInitial;

public class Groupe {
    private int numero;
    private String nom;
    private Professeur professeur;

    public Groupe(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }
    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }
    

    @Override
    public String toString() {
        return "Groupe{" +
                "numero=" + numero +
                ", nom='" + nom + '\'' +
                ", professeur=" + professeur +
                '}';
    }
}
