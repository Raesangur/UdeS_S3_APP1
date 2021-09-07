package menufact;

import menufact.exceptions.MenuException;
import menufact.facture.Facture;
import menufact.facture.exceptions.FactureException;
import menufact.ingredients.*;
import menufact.ingredients.etat.EtatIngredientGazeux;
import menufact.ingredients.etat.EtatIngredientLiquide;
import menufact.ingredients.etat.EtatIngredientSolide;
import menufact.ingredients.exceptions.IngredientException;
import menufact.ingredients.factory.*;
import menufact.plats.PlatAuMenu;
import menufact.plats.PlatChoisi;
import menufact.plats.PlatSante;
import menufact.plats.Recette;
import menufact.plats.exception.PlatException;
import menufact.plats.state.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestMenuFact02 {

    private static class TestIngredient {
        @Test
        public void testNom() {
            // Création d'un ingrédient
            Ingredient curcuma = null;
            try{
                curcuma = new Epice("curcuma", new EtatIngredientSolide(0.1));
            }
            catch (IngredientException pe){
                System.out.println("Erreur dans la quantite d'ingredient"+ pe.getMessage());
                Assert.assertTrue(false);
            }

            // Test de la valeur donnée dans le constructeur
            System.out.println("TestGetNom : valeur retour GOOD = 'curcuma'");
            System.out.println(curcuma.getNom());
            Assert.assertEquals(curcuma.getNom(), "curcuma");

            // Test d'une nouvelle valeur
            curcuma.setNom("Curcuma");
            System.out.println("TestSetNom : valeur retour GOOD = 'Curcuma'");
            System.out.println(curcuma.getNom());
            Assert.assertEquals(curcuma.getNom(), "Curcuma");
        }

        @Test
        public void testQty() {
            // Création d'un ingrédient
            Ingredient kobeSteak = new Viande("kobe", new EtatIngredientSolide(1.5));

            // Test de la valeur donnée dans le constructeur
            System.out.println("TestGetQty : valeur retour GOOD = '1.5' (kg)");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(kobeSteak.getQty(), 1.5, 0.05);

            // Changement de la valeur
            try {
                kobeSteak.setQty(1);
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test d'ingrédient: " + ie.getMessage());
                Assert.assertTrue(false);
            }

            // Test de la nouvelle valeur
            System.out.println("TestSetQty : valeur retour GOOD = '1' (kg)");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(kobeSteak.getQty(), 1, 0.05);

            // Test avec une valeur négative (devrait throw une IngredientException)
            try {
                kobeSteak.setQty(-1);
                System.out.println("Erreur dans le test d'ingrédient, une valeur négative ne peut pas être utilisée");
                Assert.assertTrue(false);
            } catch (IngredientException ie) {
                // Test de la nouvelle valeur (la valeur négative ne devrait pas avoir été enregistrée)
                System.out.println("TestSetQty : valeur retour GOOD = '1' (kg)");
                System.out.println(kobeSteak.getQty());
                Assert.assertEquals(kobeSteak.getQty(), 1, 0.05);
            }
        }

        @Test
        public void testEtat() {
            // Création d'un ingrédient
            Ingredient chips = new Legume("lays", new EtatIngredientGazeux(8));

            // Vérification de l'état
            System.out.println("TestEtat : valeur retour GOOD = 'Gazeux'");
            System.out.println(chips.getEtat());
            Assert.assertTrue(chips.getEtat() instanceof EtatIngredientGazeux);
        }
    }


    private static class TestPlatChoisi {
        private PlatChoisi spag;
        private PlatChoisi spagError;

        public TestPlatChoisi() {
            // Création d'ingrédients
            Ingredient spaghetti = new Legume("spaghetti", new EtatIngredientSolide(0.2));
            Ingredient tomatoSauce = new Legume("tomate", new EtatIngredientLiquide(0.3));
            Ingredient meetBall = new Viande("steakHache", new EtatIngredientSolide(0.05));
            // Création d'une recette
            Recette spagBoulDeViande = new Recette(new Ingredient[]{spaghetti, tomatoSauce, meetBall});

            // Création du plat au menu
            PlatAuMenu spagBoulette = null;
            try {
                spagBoulette = new PlatAuMenu(22, "spag contenant des boulettes de viande et de la sauce tomate", 18.50);
                spagBoulette.setRecette(spagBoulDeViande);

            } catch (PlatException pe) {
                System.out.println("Erreur dans le test du Plat Choisi (création du plat au menu): " + pe.getMessage());
                Assert.assertTrue(false);
            }

            // Création de deux plats concrets à partir du plat au menu
            try {

                spag = new PlatChoisi(spagBoulette, 2);
                spagError = new PlatChoisi(spagBoulette, 15623);
            } catch (PlatException pe) {
                System.out.println("Erreur dans le test du Plat Choisi (création des plats): " + pe.getMessage());
                Assert.assertTrue(false);
            }
        }

        @Test
        public void testQty() {
            // Test de la valeur donnée dans le constructeur
            System.out.println("TestGetQty : valeur retour GOOD = '2'");
            System.out.println(spag.getQty());
            Assert.assertEquals(spag.getQty(), 2);

            // Changement de la valeur
            try {
                spag.setQty(12);
            } catch (PlatException pe) {
                System.out.println("Erreur dans le test du Plat Choisi: " + pe.getMessage());
                Assert.assertTrue(false);
            }

            // Test de la nouvelle valeur
            System.out.println("TestSetQty : valeur retour GOOD = '12'");
            System.out.println(spag.getQty());
            Assert.assertEquals(spag.getQty(), 12);

            // Test d'une valeur négative (devrait throw un PlatException)
            try {
                spag.setQty(-20);
                System.out.println("Erreur dans le test du Plat Choisi, une valeur négative ne peut pas être utilisée");
                Assert.assertTrue(false);
            } catch (PlatException ie) {
                // Test de la nouvelle valeur (la valeur négative ne devrait pas avoir été enregistrée)
                System.out.println("TestSetQty : valeur retour GOOD = '12'");
                System.out.println(spag.getQty());
                Assert.assertEquals(spag.getQty(), 12, 0.05);
            }
        }

        @Test
        public void testPlat() {
            // Test le contenu d'un plat au menu
            System.out.println("TestgetPlat : valeur retour GOOD = {'code':22,'description':'spag contenant des boulettes de viande et de la sauce tomate','prix':'18.50'}");
            System.out.println(spag.getPlat());
            Assert.assertEquals(spag.getPlat().getCode(), 22);
            Assert.assertEquals(spag.getPlat().getDescription(), "spag contenant des boulettes de viande et de la sauce tomate");
            Assert.assertEquals(spag.getPlat().getPrix(), 18.50, 0.01);
        }

        @Test
        public void testEtat() {
            // Préparation des états
            Commande Commande = new Commande();
            EnPreparation EnPreparation = new EnPreparation();
            Servi Servi = new Servi();
            ErrorServir Error = new ErrorServir();
            Termine Termine = new Termine();

            // Vérifie si l'état est initialement à null
            System.out.println("TestgetEtat : valeur retour GOOD = 'Null'");
            System.out.println(spag.getEtat() == null ? "Null" : "Non-Null");
            Assert.assertEquals(null, spag.getEtat());

            // Met l'état à commandé
            try {
                spag.setEtat(Commande);
                System.out.println(spag.getEtat());

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Commandé'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Commande);
            } catch (PlatException pe) {
                System.out.println("TestError : valeur retour GOOD = 'Imposibilite de changer vers cette etat!!! :('");
                Assert.assertTrue(false);
            }

            // Met l'état à terminé (ne devrait pas être possible)
            try {
                spag.setEtat(Termine);

                // On ne devrait pas être rendu ici
                System.out.println("Error avec l'erreur");
                Assert.assertTrue(false);
            } catch (PlatException pe) {
                System.out.println("Imposibilite de changer vers cette etat!!! :(");
                System.out.println("TestgetEtat : valeur retour GOOD = 'Commandé'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Commande);
            }

            // Met l'état à commandé
            try {
                spag.setEtat(EnPreparation);

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'En préparation'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof EnPreparation);
            } catch (PlatException pe) {
                System.out.println("TestError : valeur retour GOOD = 'Imposibilite de changer vers cette etat!!! :('");
                Assert.assertTrue(false);
            }

            // Met l'état à Terminé
            try {
                spag.setEtat(Termine);

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Terminé'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Termine);
            } catch (PlatException pe) {
                System.out.println("TestError : valeur retour GOOD = 'Imposibilite de changer vers cette etat!!! :('");
                Assert.assertTrue(false);
            }

            // Met l'état à terminé
            try {
                spag.setEtat(Termine);

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Termine'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Termine);
            } catch (PlatException pe) {
                System.out.println("TestError : valeur retour GOOD = 'Imposibilite de changer vers cette etat!!! :('");
                Assert.assertTrue(false);
            }

            // Met l'état à servi
            try {
                spag.setEtat(Servi);

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Servi'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Servi);
            } catch (PlatException pe) {
                System.out.println("TestError : valeur retour GOOD = 'Imposibilite de changer vers cette etat!!! :('");
                Assert.assertTrue(false);
            }

            // Met l'état à commandé
            try {
                spag.setEtat(Error);

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Error'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Error);
            } catch (PlatException pe) {
                System.out.println("TestError : valeur retour GOOD = 'Imposibilite de changer vers cette etat!!! :('");
                Assert.assertTrue(false);
            }
        }
    }

    private class TestFactoryIngredient {
        public void TestCreerFruit() {
            // Crée une factory de fruit
            CreatorIngredient factory = new ConcreteCreatorFruit();
            Ingredient pomme = null;

            // Crée un fruit avec la factory
            try {
                pomme = factory.creer("pomme", new EtatIngredientSolide(0.2));
            }
            catch (IngredientException pe){
                System.out.println("Erreur dans la quantite d'ingredient"+ pe.getMessage());
                Assert.assertTrue(false);
            }

            // Test le fruit créé
            System.out.println("TestFactoryFruit : valeur retour GOOD = 'pomme'");
            System.out.println(pomme.getNom());
            Assert.assertEquals(pomme.getNom(), "pomme");

            System.out.println("TestFactoryFruit : valeur retour GOOD = '0.2kg'");
            System.out.println(pomme.getQty() + "kg");
            Assert.assertEquals(pomme.getQty(), 0.2, 0.05);

            System.out.println("TestFactoryFruit : valeur retour GOOD = 'Solide': {'Qty (kg)': 0.2}");
            System.out.println(pomme.getEtat());
            Assert.assertTrue(pomme.getEtat() instanceof EtatIngredientSolide);
        }

        public void TestCreerLegume() {
            // Crée une factory de légumes
            CreatorIngredient factory = new ConcreteCreatorLegume();
            Ingredient concombre = null;

            // Crée une légume avec la factory
            try {
                concombre = factory.creer("concombre", new EtatIngredientSolide(0.5));
            }
            catch (IngredientException pe){
                System.out.println("Erreur dans la quantite d'ingredient"+ pe.getMessage());
                Assert.assertTrue(false);
            }

            // Test le légume créé
            System.out.println("TestFactoryLegumes : valeur retour GOOD = 'concombre'");
            System.out.println(concombre.getNom());
            Assert.assertEquals(concombre.getNom(), "concombre");

            System.out.println("TestFactoryLegumes : valeur retour GOOD = '0.5kg'");
            System.out.println(concombre.getQty() + "kg");
            Assert.assertEquals(concombre.getQty(), 0.5, 0.05);

            System.out.println("TestFactoryLegumes : valeur retour GOOD = 'Solide': {'Qty (kg)': 0.5}");
            System.out.println(concombre.getEtat());
            Assert.assertTrue(concombre.getEtat() instanceof EtatIngredientSolide);
        }

        public void TestCreerEpice() {
            // Crée une factory d'épice
            CreatorIngredient factory = new ConcreteCreatorEpice();
            Ingredient sriracha = null;

            // Crée une épice avec la factory
            try {
                sriracha = factory.creer("Sriracha", new EtatIngredientLiquide(1));
            }
            catch (IngredientException pe){
                System.out.println("Erreur dans la quantite d'ingredient"+ pe.getMessage());
                Assert.assertTrue(false);
            }

            // Test l'épice
            System.out.println("TestFactoryEpice : valeur retour GOOD = 'Sriracha'");
            System.out.println(sriracha.getNom());
            Assert.assertEquals(sriracha.getNom(), "Sriracha");

            System.out.println("TestFactoryEpice : valeur retour GOOD = '1L'");
            System.out.println(sriracha.getQty() + "L");
            Assert.assertEquals(sriracha.getQty(), 1, 0.05);

            System.out.println("TestFactoryEpice : valeur retour GOOD = 'Liquide': {'Qty (L)': 1}");
            System.out.println(sriracha.getEtat());
            Assert.assertTrue(sriracha.getEtat() instanceof EtatIngredientLiquide);
        }

        public void TestCreerLaitier() {
            // Crée une factory de produits laitiers
            CreatorIngredient factory = new ConcreteCreatorLaitier();
            Ingredient lait = null;

            // Crée un produit laitier avec la factory
            try {
                lait = factory.creer("Lait", new EtatIngredientLiquide(4));
            }
            catch (IngredientException pe){
                System.out.println("Erreur dans la quantite d'ingredient"+ pe.getMessage());
                Assert.assertTrue(false);
            }

            // Test le produit laitier
            System.out.println("TestFactoryLaitier : valeur retour GOOD = 'Lait'");
            System.out.println(lait.getNom());
            Assert.assertEquals(lait.getNom(), "Lait");

            System.out.println("TestFactoryLaitier : valeur retour GOOD = '4L'");
            System.out.println(lait.getQty() + "L");
            Assert.assertEquals(lait.getQty(), 4, 0.05);

            System.out.println("TestFactoryLaitier : valeur retour GOOD = ''Liquide': {'Qty (L)': 4}'");
            System.out.println(lait.getEtat());
            Assert.assertTrue(lait.getEtat() instanceof EtatIngredientLiquide);
        }

        public void TestCreerViande() {
            // Crée une factory de viande
            CreatorIngredient factory = new ConcreteCreatorViande();
            Ingredient coteLevee = null;

            // Crée une viande avec la factory
            try {
                coteLevee = factory.creer("coteLevee", new EtatIngredientSolide(0.454));
            }
            catch (IngredientException pe){
                System.out.println("Erreur dans la quantite d'ingredient"+ pe.getMessage());
                Assert.assertTrue(false);
            }

            // Test la viande
            System.out.println("TestFactoryViande : valeur retour GOOD = 'coteLevee'");
            System.out.println(coteLevee.getNom());
            Assert.assertEquals(coteLevee.getNom(), "coteLevee");

            System.out.println("TestFactoryViande : valeur retour GOOD = '0.454 kg'");
            System.out.println(coteLevee.getQty() + "kg");
            Assert.assertEquals(coteLevee.getQty(), 0.454, 0.05);

            System.out.println("TestFactoryViande : valeur retour GOOD = ''Solide': {'Qty (kg)': 0.454}''");
            System.out.println(coteLevee.getEtat());
            Assert.assertTrue(coteLevee.getEtat() instanceof EtatIngredientSolide);
        }
    }

    private class testChef {
        Chef Zeff;
        Inventaire congelateur;

        Ingredient tomatoSauce;
        Ingredient pepperoni;
        Ingredient bacon;
        Ingredient cheese;
        Ingredient pate;

        Recette piz;
        PlatAuMenu pizz;
        PlatChoisi pizza;
        PlatChoisi pizza2;


        public testChef() {
            Zeff = Chef.getInstance();
            Zeff.setNom("Zeff");
            congelateur = Inventaire.getInstance();

            tomatoSauce = new Legume("tomate", new EtatIngredientLiquide(0.5));
            pepperoni = new Viande("pepperoni", new EtatIngredientSolide(0.2));
            bacon = new Viande("bacon", new EtatIngredientSolide(0.2));
            cheese = new Laitier("cheese", new EtatIngredientSolide(1));
            pate = new Fruit("pate", new EtatIngredientSolide(0.454));

            piz = new Recette(new Ingredient[]{pepperoni, tomatoSauce, bacon, cheese, pate});

            try{
                PlatAuMenu pizz = new PlatAuMenu(69, "pizza pepperoni bacon", 60);
            }
            catch (PlatException pe){
                System.out.println("Erreur dans le plat au menu"+ pe.getMessage());
                Assert.assertTrue(false);
            }
            pizz.setRecette(piz);
            try {
                pizza2 = new PlatChoisi(pizz, 2);
            }
            catch (PlatException pe){
                System.out.println("Erreur dans le plat choisie"+ pe.getMessage());
                Assert.assertTrue(false);
            }



            try {
                congelateur.ajouterIngredient(new Ingredient[]{pepperoni, tomatoSauce, bacon, cheese, pate});
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test du chef: " + ie.getMessage());
                Assert.assertTrue(false);
            }
        }

        ;

        public void testNom() {
            System.out.println("TestChefNom : valeur retour GOOD = 'Zeff'");
            Assert.assertEquals(Zeff.getNom(), "Zeff");
            System.out.println(Zeff.getNom());
        }

        public void testCuisiner() {
            System.out.println("TestErrorIngrdient : valeur retour GOOD = 'Quantité d'ingredients insuffisant'");
            try {
                Zeff.cuisiner(pizza2);
                System.out.println("Error avec l'erreur");
                Assert.assertTrue(pizza.getEtat() instanceof Servi);
            } catch (IngredientException Ie) {
                System.out.println("Quantité d'ingredients insuffisant");
                Assert.assertTrue(pizza2.getEtat() instanceof ErrorServir);
            } catch (PlatException pe) {
                System.out.println("Erreur de Changement d'etat");
                Assert.assertTrue(false);
            }
            System.out.println("TestErrorIngrdient : valeur retour GOOD = 'Servi'");
            try {
                Zeff.cuisiner(pizza);
                System.out.println(pizza.getEtat());
                Assert.assertTrue(pizza.getEtat() instanceof Servi);
            } catch (IngredientException Ie) {
                System.out.println("Quantité d'ingredients insuffisant");
                Assert.assertTrue(pizza.getEtat() instanceof ErrorServir);
            } catch (PlatException pe) {
                System.out.println("Erreur de Changement d'etat");
                Assert.assertTrue(false);
            }
        }
    }

    private class TestRecette {

        Recette recetteSteak;
        Ingredient steak;
        Ingredient sel;
        Ingredient poivre;
        Ingredient tomate;
        Ingredient laitue;

        // Pas besoin de @BeforeEach avant un constructeur
        TestRecette() {
            steak = new Viande("Steak", new EtatIngredientSolide(0.3));
            sel = new Epice("Sel", new EtatIngredientSolide(0.02));
            poivre = new Epice("Poivre", new EtatIngredientSolide(0.01));
            tomate = new Legume("Tomate", new EtatIngredientSolide(0.1));
            laitue = new Legume("Laitue", new EtatIngredientSolide(0.05));

            recetteSteak = new Recette(new Ingredient[]{steak, sel, poivre});
        }

        @Test
        public void testRecette() {
            System.out.println("===Test Recette Steak + Sel/Poivre");
            System.out.println(recetteSteak);

            List<Ingredient> listeIngredient = recetteSteak.getIngredients();
            Assert.assertEquals(steak, listeIngredient.get(0));
            Assert.assertEquals(sel, listeIngredient.get(1));
            Assert.assertEquals(poivre, listeIngredient.get(2));
            Assert.assertEquals(0.3, listeIngredient.get(0).getQty(), 0.005);
        }


        @Test
        public void testAddIngredient() {
            testRecette();
            recetteSteak.addIngredient(tomate);
            System.out.println("===Test Recette Ajout de tomate");
            System.out.println(recetteSteak);

            Assert.assertEquals(tomate, recetteSteak.getIngredients().get(3));
            Assert.assertEquals(4, recetteSteak.getIngredients().size());
        }

        @Test
        public void testSetIngredients() {
            testRecette();

            recetteSteak.setIngredients(new Ingredient[]{laitue, tomate, sel});

            System.out.println("===Test Recette plat steak végétarien");
            System.out.println(recetteSteak);

            Assert.assertEquals(3, recetteSteak.getIngredients().size());
            Assert.assertFalse(recetteSteak.getIngredients().contains(steak));
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
