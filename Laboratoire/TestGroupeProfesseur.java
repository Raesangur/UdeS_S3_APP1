package codeInitial;

public class TestGroupeProfesseur {

    public static void main(String[] args)
    {
        Groupe groupe = new Groupe(1,"Design Patterns");
        Professeur professeur1 = Professeur.getInstance();
        professeur1.setCip("abcd1234");
        professeur1.setNom("Mr Professeur 1");

        System.out.println("Le groupe : " + groupe);
        groupe.setProfesseur(professeur1);
        System.out.println("Le groupe : " + groupe);

        groupe.setProfesseur(Professeur.getInstance());
        System.out.println("Le groupe : " + groupe);

    }
}
