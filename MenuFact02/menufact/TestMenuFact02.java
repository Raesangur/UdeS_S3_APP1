package menufact;

import menufact.exceptions.MenuException;
import menufact.facture.Facture;
import menufact.facture.exceptions.FactureException;
import menufact.ingredients.Epice;
import menufact.ingredients.Ingredient;
import menufact.ingredients.Legume;
import menufact.ingredients.Viande;
<<<<<<< Updated upstream
import menufact.ingredients.etat.EtatIngredientSolide;
import menufact.ingredients.factory.ConcreteCreatorLaitier;
import menufact.ingredients.factory.CreatorIngredient;
=======
import menufact.ingredients.etat.EtatIngredientGazeux;
import menufact.ingredients.etat.EtatIngredientLiquide;
import menufact.ingredients.etat.EtatIngredientSolide;
>>>>>>> Stashed changes
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;
import menufact.plats.PlatSante;
import menufact.plats.Recette;
import org.junit.Assert;
import org.junit.Test;
<<<<<<< Updated upstream
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
=======
>>>>>>> Stashed changes

public class TestMenuFact02 {
    private static class TestIngredient {
        @Test
        public void testNom() {

            Ingredient curcuma = new Epice("curcuma",new EtatIngredientSolide(0.1));
            System.out.println("TestGetNom : valeur retour GOOD = 'curcuma'");
            System.out.println(curcuma.getNom());
            Assert.assertEquals(curcuma.getNom(), "curcuma");
            curcuma.setNom("Curcuma");
            System.out.println("TestSetNom : valeur retour GOOD = 'Curcuma'");
            System.out.println(curcuma.getNom());
            Assert.assertEquals(curcuma.getNom(), "Curcuma");

        };
        @Test
        public void testQty() {
            Ingredient kobeSteak = new Viande("kobe",new EtatIngredientSolide(1.5);
            System.out.println("TestGetQty : valeur retour GOOD = '1.5kg'");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(kobeSteak.getQty(), 1.5,0.05);
            kobeSteak.setQty(1);
            System.out.println("TestSetQty : valeur retour GOOD = '1 kg'");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(kobeSteak.getQty(), 1,0.05);
        };
        @Test
        public void testEtat(){
            Ingredient chips = new Legume("lays",new EtatIngredientGazeux(8);
            System.out.println("TestEtat : valeur retour GOOD = 'Gazeux'");
            Assert.assertTrue(chips.getEtat() instanceof EtatIngredientGazeux);
        }

    }


    private static class TestPlatChoisi {

        public TestPlatChoisi() {

            Ingredient spaghetti = new Legume("spaghetti",new EtatIngredientSolide(0.2));
            Ingredient tomatoSauce = new Legume("tomate",new EtatIngredientLiquide(0.3));
            Ingredient meetBall = new Viande("steakHache",new EtatIngredientSolide(0.05));
            Recette spagBoulDeViande = new Recette(new Ingredient[]{spaghetti,tomatoSauce,meetBall});


        };
        @Test
        public void testQty() {
            Ingredient kobeSteak = new Viande("kobe",new EtatIngredientSolide(1.5);
            System.out.println("TestGetQty : valeur retour GOOD = '1.5kg'");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(kobeSteak.getQty(), 1.5,0.05);
            kobeSteak.setQty(1);
            System.out.println("TestSetQty : valeur retour GOOD = '1 kg'");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(kobeSteak.getQty(), 1,0.05);
        };
        @Test
        public void testPlat(){
            Ingredient chips = new Legume("lays",new EtatIngredientGazeux(8);
            System.out.println("TestEtat : valeur retour GOOD = 'Gazeux'");
            Assert.assertTrue(chips.getEtat() instanceof EtatIngredientGazeux);
        }
        @Test
        public void testEtat(){
            Ingredient chips = new Legume("lays",new EtatIngredientGazeux(8);
            System.out.println("TestEtat : valeur retour GOOD = 'Gazeux'");
            Assert.assertTrue(chips.getEtat() instanceof EtatIngredientGazeux);
        }

    }

    private class TestRecette {

        Recette recetteSteak;
        Ingredient steak;
        Ingredient sel;
        Ingredient poivre;
        Ingredient tomate;

        // Pas besoin de @BeforeEach avant un constructeur
        TestRecette() {
            steak = new Viande("Steak", new EtatIngredientSolide(0.3));
            sel = new Epice("Sel", new EtatIngredientSolide(0.02));
            poivre = new Epice("Poivre", new EtatIngredientSolide(0.01));
            tomate = new Legume("Tomate", new EtatIngredientSolide(0.1));

            recetteSteak = new Recette(new Ingredient[]{steak, sel, poivre});
        }

        @Test
        public void testRecette() {
            System.out.println("===Test Recette Steak + Sel/Poivre");
            System.out.println(recetteSteak);

            List<Ingredient> listeIngredient = recetteSteak.getIngredients();
            Assert.assertEquals(listeIngredient.get(0), steak);
            Assert.assertEquals(listeIngredient.get(1), sel);
            Assert.assertEquals(listeIngredient.get(2), poivre);

        }

        @Test
        public void testAddIngredient() {
            recetteSteak.addIngredient(tomate);

            System.out.println("===Test Recette Ajout de tomate");
            System.out.println(recetteSteak);

            Assert.assertEquals(recetteSteak.getIngredients().get(3), tomate);
            Assert.assertEquals(recetteSteak.getIngredients().size(), 4);
        }
    }

    public static void main(String[] args) throws FactureException {
        boolean trace = true;
        Assert.assertTrue(trace);

        TestMenuFact02 t = new TestMenuFact02();

        PlatAuMenu p1 = new PlatAuMenu(0, "PlatAuMenu0", 10);
        PlatAuMenu p2 = new PlatAuMenu(1, "PlatAuMenu1", 20);
        PlatAuMenu p3 = new PlatAuMenu(2, "PlatAuMenu2", 30);
        PlatAuMenu p4 = new PlatAuMenu(3, "PlatAuMenu3", 40);
        PlatAuMenu p5 = new PlatAuMenu(4, "PlatAuMenu4", 50);


        PlatSante ps1 = new PlatSante(10, "PlatSante0", 10, 11, 11, 11);
        PlatSante ps2 = new PlatSante(11, "PlatSante1", 20, 11, 11, 11);
        PlatSante ps3 = new PlatSante(12, "PlatSante2", 30, 11, 11, 11);
        PlatSante ps4 = new PlatSante(13, "PlatSante3", 40, 11, 11, 11);
        PlatSante ps5 = new PlatSante(14, "PlatSante4", 50, 11, 11, 11);


        Menu m1 = new Menu("menufact.Menu 1");
        Menu m2 = new Menu("menufact.Menu 2");

        Facture f1 = new Facture("Ma facture");

        Client c1 = new Client(1, "Mr Client", "1234567890");


        t.test1_AffichePlatsAuMenu(trace, p1, p2, p3, p4, p5);
        t.test2_AffichePlatsSante(trace, ps1, ps2, ps3, ps4, ps5);

        t.test4_AjoutPlatsAuMenu(trace, m1, p1, p2, ps1, ps2, m2, p3, p4, ps3, ps4);


        try {
            t.test5_DeplacementMenuAvancer(m1);
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }

        try {
            t.test6_DeplacementMenuReculer(m1);
        } catch (MenuException e) {
            System.out.println(e.getMessage());
        }

        try {
            t.test7_CreerFacture(f1, m1);
        } catch (FactureException e) {
            System.out.println(e.getMessage());
        }


        t.test8_AjouterClientFacture(f1, c1);

        try {
            t.test8_AjouterPlatsFacture(f1, m1, 1);
        } catch (FactureException fe) {
            System.out.println(fe.getMessage());
        } catch (MenuException me) {
            System.out.println(me);
        }

        t.test9_PayerFacture(f1);

        try {
            t.test8_AjouterPlatsFacture(f1, m1, 1);
        } catch (FactureException fe) {
            System.out.println(fe.getMessage());
        } catch (MenuException me) {
            System.out.println(me);
        }

        try {
            f1.ouvrir();
        } catch (FactureException fe) {
            System.out.println(fe.getMessage());
        }


        System.out.println("FIN DE TOUS LES TESTS...");

        System.out.println(f1.genererFacture());
    }

    private void test1_AffichePlatsAuMenu(boolean trace, PlatAuMenu p1, PlatAuMenu p2,
                                          PlatAuMenu p3, PlatAuMenu p4, PlatAuMenu p5) {
        System.out.println("=== test1_AffichePlatsAuMenu");
        if (trace) {
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
            System.out.println(p4);
            System.out.println(p5);
        }
    }


    private void test2_AffichePlatsSante(boolean trace, PlatSante ps1, PlatSante ps2,
                                         PlatSante ps3, PlatSante ps4, PlatSante ps5) {
        System.out.println("=== test2_AffichePlatsSante");

        if (trace) {
            System.out.println(ps1);
            System.out.println(ps2);
            System.out.println(ps3);
            System.out.println(ps4);
            System.out.println(ps5);
        }
    }


    private static void test3_AjoutMenu(boolean trace, Menu m1, Menu m2) {

        System.out.println("=== test3_AjoutMenu");

        if (trace) {
            System.out.println(m1);
            System.out.println(m2);
        }
    }


    private void test4_AjoutPlatsAuMenu(boolean trace, Menu m1,
                                        PlatAuMenu p1, PlatAuMenu p2,
                                        PlatSante ps1, PlatSante ps2,
                                        Menu m2,
                                        PlatAuMenu p3, PlatAuMenu p4,
                                        PlatSante ps3, PlatSante ps4) {
        System.out.println("=== test4_AjoutPlatsAuMenu");
        System.out.println("=== Ajout de plats au menu 1");
        m1.ajoute(p1);
        m1.ajoute(p2);
        m1.ajoute(ps1);
        m1.ajoute(ps2);


        System.out.println("=== Ajout de plats au menu 2");
        m2.ajoute(p3);
        m2.ajoute(p4);
        m2.ajoute(ps3);
        m2.ajoute(ps4);

        if (trace) {
            System.out.println(m1);
            System.out.println(m2);
        }
    }


    private void test5_DeplacementMenuAvancer(Menu m1) throws MenuException {
        System.out.println("=== test5_DeplacementMenuAvancer");

        System.out.println("===Selectionner un plat du menu 0");
        m1.position(0);

        System.out.println("=== Afficher le plat courant");
        System.out.println(m1.platCourant());
        try {

            System.out.println("=== Avancer le plat courant");
            System.out.println("1.");
            m1.positionSuivante();
            System.out.println("2.");
            m1.positionSuivante();
            System.out.println("3.");
            m1.positionSuivante();
            System.out.println("4.");
            m1.positionSuivante();
            System.out.println("5.");
            m1.positionSuivante();
        } catch (MenuException me) {
            throw me;
        }
    }


    private void test6_DeplacementMenuReculer(Menu m1) throws MenuException {
        System.out.println("===test6_DeplacementMenuReculer");

        System.out.println("===Selectionner un plat du menu 3");
        m1.position(3);

        System.out.println("=== Afficher le plat courant");
        System.out.println(m1.platCourant());
        try {

            System.out.println("=== Reculer le plat courant");
            System.out.println("2.");
            m1.positionPrecedente();
            System.out.println("1.");
            m1.positionPrecedente();
            System.out.println("0.");
            m1.positionPrecedente();
            System.out.println("-1.");
            m1.positionPrecedente();
            System.out.println("-2.");
            m1.positionPrecedente();
        } catch (MenuException me) {
            throw me;
        }
    }

    private void test7_CreerFacture(Facture f1, Menu m1) throws FactureException {
        System.out.println("===test7_CreerFacture");

        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(), 5);
        try {
            f1.ajoutePlat(platChoisi);
        } catch (FactureException fe) {
            throw fe;
        }
        System.out.println(f1);
    }


    private void test8_AjouterClientFacture(Facture f1, Client c1) {
        System.out.println("===test8_AjouterClientFacture");
        f1.associerClient(c1);
        System.out.println(f1);
    }

    private void test8_AjouterPlatsFacture(Facture f1, Menu m1, int pos) throws MenuException, FactureException {
        System.out.println("===test8_AjouterPlatsFacture");

        try {
            for (int i = 0; i < pos; i++)
                m1.positionSuivante();
        } catch (MenuException me) {
            throw me;
        }

        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(), 5);
        try {
            f1.ajoutePlat(platChoisi);
        } catch (FactureException fe) {
            throw fe;
        }
        System.out.println(f1);
    }

    private void test9_PayerFacture(Facture f1) throws FactureException {
        System.out.println("===test9_PayerFacture");

        System.out.println("Avant payer la facture");
        System.out.println(f1);

        f1.payer();
        System.out.println("Apres avoir paye la facture");
        System.out.println(f1);
    }
}
