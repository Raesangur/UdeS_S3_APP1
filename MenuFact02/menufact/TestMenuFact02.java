package menufact;

import menufact.exceptions.MenuException;
import menufact.facture.exceptions.FactureException;
import menufact.ingredients.exceptions.IngredientException;
import menufact.plats.exception.PlatException;

import menufact.facture.Facture;
import menufact.facture.state.FactureEtatFermee;
import menufact.facture.state.FactureEtatOuverte;

import menufact.ingredients.*;
import menufact.ingredients.etat.EtatIngredientGazeux;
import menufact.ingredients.etat.EtatIngredientLiquide;
import menufact.ingredients.etat.EtatIngredientSolide;
import menufact.ingredients.factory.*;

import menufact.plats.*;
import menufact.plats.builder.PlatBuilder;
import menufact.plats.builder.PlatEnfantBuilder;
import menufact.plats.builder.PlatSanteBuilder;
import menufact.plats.state.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestMenuFact02 {

    public static void main(String[] args) throws FactureException {
        System.out.println("=== TESTS UNITAIRES ===");
        System.out.println("=== TESTS INGRÉDIENTS ===");
        TestIngredient.runTests();

        System.out.println("\n=== TESTS PLAT CHOISI ===");
        TestPlatChoisi.runTests();

        System.out.println("\n=== TESTS FACTORY D'INGRÉDIENTS ===");
        TestFactoryIngredient.runTests();

        System.out.println("\n=== TESTS CHEF ===");
        TestChef.runTests();

        System.out.println("\n=== TESTS RECETTE ===");
        TestRecette.runTests();

        System.out.println("\n=== TESTS INVENTAIRE ===");
        TestInventaire.runTests();

        System.out.println("\n=== TESTS PLATS & PLAT BUILDER ===");
        TestPlat.runTests();

        System.out.println("\n=== TESTS CLIENT ===");
        TestClient.runTests();

        System.out.println("\n=== TESTS D'INTÉGRATIONS ===");
        TestMenuFact.runTests();

        System.out.println("\n=== FIN DES TESTS ===");

        //test0_base();
    }


    /**
     * Classe de test pour tester les ingrédients
     */
    private static class TestIngredient {

        /**
         * Roule tous les tests sur les ingrédients
         */
        @Test
        public static void runTests() {
            testNom();
            testQty();
            testEtat();
        }

        @Test
        public static void testNom() {
            // Création d'un ingrédient
            Ingredient curcuma = null;
            try {
                curcuma = new Epice("curcuma", new EtatIngredientSolide(0.1));
            } catch (IngredientException ie) {
                System.out.println("Erreur dans la quantite d'ingredient" + ie.getMessage());
                Assert.fail();
            }

            // Test de la valeur donnée dans le constructeur
            System.out.println("TestGetNom : valeur retour GOOD = 'curcuma'");
            System.out.println(curcuma.getNom());
            Assert.assertEquals("curcuma", curcuma.getNom());

            // Test d'une nouvelle valeur
            curcuma.setNom("Curcuma");
            System.out.println("TestSetNom : valeur retour GOOD = 'Curcuma'");
            System.out.println(curcuma.getNom());
            Assert.assertEquals("Curcuma", curcuma.getNom());

            //Test d'une nouvelle valeur nulle
            curcuma.setNom(null);
            System.out.println("TestSetNull : valeur retour GOOD = 'Curcuma'");
            System.out.println(curcuma.getNom());
            Assert.assertEquals("Curcuma", curcuma.getNom());
        }

        @Test
        public static void testQty() {
            // Création d'un ingrédient
            Ingredient kobeSteak = null;
            try {
                kobeSteak = new Viande("kobe", new EtatIngredientSolide(1.5));
            } catch (IngredientException ie) {
                System.out.println("Erreur dans la quantite d'ingredient" + ie.getMessage());
                Assert.fail();
            }

            // Test de la valeur donnée dans le constructeur
            System.out.println("TestGetQty : valeur retour GOOD = '1.5' (kg)");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(kobeSteak.getQty(), 1.5, 0.05);

            // Changement de la valeur
            try {
                kobeSteak.setQty(1);
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test d'ingrédient: " + ie.getMessage());
                Assert.fail();
            }

            // Test de la nouvelle valeur
            System.out.println("TestSetQty : valeur retour GOOD = '1' (kg)");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(1, kobeSteak.getQty(), 0.05);

            //changement de la valeur de 0
            try {
                kobeSteak.setQty(0);
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test d'ingrédient: " + ie.getMessage());
                Assert.fail();
            }

            // Test de la nouvelle valeur 0
            System.out.println("TestSet0 : valeur retour GOOD = '0' (kg)");
            System.out.println(kobeSteak.getQty());
            Assert.assertEquals(0, kobeSteak.getQty(), 0.05);

            //changement de la valeur de -1
            System.out.println("TestSetNégatif : valeur retour GOOD = 'Task Failed Successfully'");
            // Test avec une valeur négative (devrait throw une IngredientException)
            try {
                kobeSteak.setQty(-1);
                System.out.println("Error dans L'Erreur");
                Assert.fail();
            } catch (IngredientException ie) {
                // Test de la nouvelle valeur (la valeur négative ne devrait pas avoir été enregistrée)
                System.out.println("Task Failed Successfully");
                System.out.println("TestSetQty : valeur retour GOOD = '0' (kg)");
                System.out.println(kobeSteak.getQty());
                Assert.assertEquals(0, kobeSteak.getQty(), 0.05);
            }
        }

        @Test
        public static void testEtat() {
            // Création d'un ingrédient
            Ingredient chipsLays = null;
            Ingredient merde = null;
            try {
                // Les sacs de chips sont à 90% pleins de gaz donc comptent comme gazeux
                chipsLays = new Legume("lays", new EtatIngredientGazeux(8));
            } catch (IngredientException ie) {
                System.out.println("Erreur dans la quantite d'ingredient" + ie.getMessage());
                Assert.fail();
            }

            // Vérification de l'état
            System.out.println("TestEtat : valeur retour GOOD = 'Gazeux'");
            System.out.println(chipsLays.getEtat());
            Assert.assertTrue(chipsLays.getEtat() instanceof EtatIngredientGazeux);

            //test avec État null
            System.out.println("TestEtatNULL : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                merde = new Legume("vide", null);
                System.out.println("Error dans L'Erreur");
                Assert.fail();
            } catch (IngredientException ie) {
                System.out.println("Task Failed Successfully");
            }

        }
    }

    private static class TestPlatChoisi {
        private PlatChoisi spag;
        private PlatChoisi spagError;

        public TestPlatChoisi() {
            // Création d'ingrédients
            Ingredient spaghetti, tomatoSauce, meetBall;
            spaghetti = tomatoSauce = meetBall = null;
            try {
                spaghetti = new Legume("spaghetti", new EtatIngredientSolide(0.2));
                tomatoSauce = new Legume("tomate", new EtatIngredientLiquide(0.3));
                meetBall = new Viande("steakHache", new EtatIngredientSolide(0.05));
            } catch (IngredientException ie) {
                System.out.println("Erreur dans la quantite d'ingredient" + ie.getMessage());
                Assert.fail();
            }

            // Création d'une recette
            Recette spagBoulDeViande = new Recette(new Ingredient[]{spaghetti, tomatoSauce, meetBall});

            // Création du plat au menu
            PlatAuMenu spagBoulette = null;
            try {
                spagBoulette = new PlatAuMenu(22, "spag contenant des boulettes de viande et de la sauce tomate", 18.50);
                spagBoulette.setRecette(spagBoulDeViande);

            } catch (PlatException pe) {
                System.out.println("Erreur dans le test du Plat Choisi (création du plat au menu): " + pe.getMessage());
                Assert.fail();
            }

            // Création de deux plats concrets à partir du plat au menu
            try {

                spag = new PlatChoisi(spagBoulette, 2);
                spagError = new PlatChoisi(spagBoulette, 15623);
            } catch (PlatException pe) {
                System.out.println("Erreur dans le test du Plat Choisi (création des plats): " + pe.getMessage());
                Assert.fail();
            }

            //quantité négative de plat
            System.out.println("TestNbrPlatNégatif : valeur retour GOOD = 'Task Failed Successfully'");
            try {

                spag = new PlatChoisi(spagBoulette, -1);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
            }
        }

        @Test
        public static void runTests() {
            new TestPlatChoisi().testQty();
            new TestPlatChoisi().testPlat();
            new TestPlatChoisi().testEtat();
        }

        @Test
        public void testQty() {
            // Test de la valeur donnée dans le constructeur
            System.out.println("TestGetQty : valeur retour GOOD = '2'");
            System.out.println(spag.getQty());
            Assert.assertEquals(2, spag.getQty());

            // Changement de la valeur
            try {
                spag.setQty(12);
            } catch (PlatException pe) {
                System.out.println("Erreur dans le test du Plat Choisi: " + pe.getMessage());
                Assert.fail();
            }

            // Test de la nouvelle valeur
            System.out.println("TestSetQty : valeur retour GOOD = '12'");
            System.out.println(spag.getQty());
            Assert.assertEquals(12, spag.getQty());

            // Test d'une valeur négative (devrait throw un PlatException)
            try {
                spag.setQty(-20);
                System.out.println("Erreur dans le test du Plat Choisi, une valeur négative ne peut pas être utilisée");
                Assert.fail();
            } catch (PlatException ie) {
                // Test de la nouvelle valeur (la valeur négative ne devrait pas avoir été enregistrée)
                System.out.println("TestSetQty : valeur retour GOOD = '12'");
                System.out.println(spag.getQty());
                Assert.assertEquals(12, spag.getQty(), 0.05);
            }

            //test de la nouvelle valeur de 0
            try {
                spag.setQty(0);
            } catch (PlatException pe) {
                System.out.println("Erreur dans le test du Plat Choisi: " + pe.getMessage());
                Assert.fail();
            }

            // Test de la nouvelle valeur
            System.out.println("TestSetQty : valeur retour GOOD = '0'");
            System.out.println(spag.getQty());
            Assert.assertEquals(0, spag.getQty());
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
            Assert.assertNull(spag.getEtat());

            // Met l'état à commandé
            try {
                spag.setEtat(Commande);
                System.out.println(spag.getEtat());

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Commandé'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Commande);
            } catch (PlatException pe) {
                System.out.println("TestEtat: " + pe.getMessage());
                Assert.fail();
            }

            // Met l'état à terminé (ne devrait pas être possible)
            try {
                spag.setEtat(Termine);

                // On ne devrait pas être rendu ici
                System.out.println("Error avec l'erreur");
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
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
                System.out.println("TestEtat: " + pe.getMessage());
                Assert.fail();
            }

            // Met l'état à Terminé
            try {
                spag.setEtat(Termine);

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Terminé'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Termine);
            } catch (PlatException pe) {
                System.out.println("TestEtat: " + pe.getMessage());
                Assert.fail();
            }

            // Met l'état à terminé à nouveau, ne devrait pas fonctionner
            try {
                spag.setEtat(Termine);

                // On ne devrait pas être rendu ici
                System.out.println("Error avec l'erreur");
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task failed successfully");
                System.out.println("TestgetEtat : valeur retour GOOD = 'Terminé'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Termine);
            }

            // Met l'état à servi
            try {
                spag.setEtat(Servi);

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Servi'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof Servi);
            } catch (PlatException pe) {
                System.out.println("TestEtat: " + pe.getMessage());
                Assert.fail();
            }

            // Met l'état à d'erreur
            try {
                spag.setEtat(Error);

                // Test l'état
                System.out.println("TestgetEtat : valeur retour GOOD = 'Error'");
                System.out.println(spag.getEtat());
                Assert.assertTrue(spag.getEtat() instanceof ErrorServir);
            } catch (PlatException pe) {
                System.out.println("TestEtat: " + pe.getMessage());
                Assert.fail();
            }
        }
    }

    private static class TestFactoryIngredient {

        @Test
        public static void runTests() {
            testCreerFruit();
            testCreerLegume();
            testCreerEpice();
            testCreerLaitier();
            testCreerViande();
        }

        @Test
        public static void testCreerFruit() {
            // Crée une factory de fruit
            CreatorIngredient factory = new ConcreteCreatorFruit();
            Ingredient pomme = null;

            // Crée un fruit avec la factory
            try {
                pomme = factory.creer("pomme", new EtatIngredientSolide(0.2));
            } catch (IngredientException pe) {
                System.out.println("Erreur dans la quantite d'ingredient" + pe.getMessage());
                Assert.fail();
            }
            // Créer un fruit quantité négative
            System.out.println("TestCréer fruit avec quantité négative : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                pomme = factory.creer("pomme", new EtatIngredientSolide(-1));
                Assert.fail();
            } catch (IngredientException pe) {
                System.out.println("Task Failed Successfully");
            }


            // Test le fruit créé
            System.out.println("TestFactoryFruit : valeur retour GOOD = 'pomme'");
            System.out.println(pomme.getNom());
            Assert.assertEquals("pomme", pomme.getNom());

            System.out.println("TestFactoryFruit : valeur retour GOOD = '0.2kg'");
            System.out.println(pomme.getQty() + "kg");
            Assert.assertEquals(0.2, pomme.getQty(), 0.05);

            System.out.println("TestFactoryFruit : valeur retour GOOD = 'Solide': {'Qty (kg)': 0.2}");
            System.out.println(pomme.getEtat());
            Assert.assertTrue(pomme.getEtat() instanceof EtatIngredientSolide);

            //Test set Nom vers nom null
            System.out.println("TestSetNomNull : valeur retour GOOD = 'pomme'");
            pomme.setNom(null);
            System.out.println(pomme.getNom());
            Assert.assertEquals("pomme", pomme.getNom());
        }

        @Test
        public static void testCreerLegume() {
            // Crée une factory de légumes
            CreatorIngredient factory = new ConcreteCreatorLegume();
            Ingredient concombre = null;

            // Crée une légume avec la factory
            try {
                concombre = factory.creer("concombre", new EtatIngredientSolide(0.5));
            } catch (IngredientException pe) {
                System.out.println("Erreur dans la quantite d'ingredient" + pe.getMessage());
                Assert.fail();
            }

            // Créer un legume quantité négative
            System.out.println("TestCréer fruit avec quantité négative : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                concombre = factory.creer("concombre", new EtatIngredientSolide(-1));
                Assert.fail();
            } catch (IngredientException pe) {
                System.out.println("Task Failed Successfully");
            }

            // Test le légume créé
            System.out.println("TestFactoryLegumes : valeur retour GOOD = 'concombre'");
            System.out.println(concombre.getNom());
            Assert.assertEquals("concombre", concombre.getNom());

            System.out.println("TestFactoryLegumes : valeur retour GOOD = '0.5kg'");
            System.out.println(concombre.getQty() + "kg");
            Assert.assertEquals(0.5, concombre.getQty(), 0.05);

            System.out.println("TestFactoryLegumes : valeur retour GOOD = 'Solide': {'Qty (kg)': 0.5}");
            System.out.println(concombre.getEtat());
            Assert.assertTrue(concombre.getEtat() instanceof EtatIngredientSolide);

            //Test set Nom vers nom null
            System.out.println("TestSetNomNull : valeur retour GOOD = 'concombre'");
            concombre.setNom(null);
            System.out.println(concombre.getNom());
            Assert.assertEquals("concombre", concombre.getNom());
        }

        @Test
        public static void testCreerEpice() {
            // Crée une factory d'épice
            CreatorIngredient factory = new ConcreteCreatorEpice();
            Ingredient sriracha = null;

            // Crée une épice avec la factory
            try {
                sriracha = factory.creer("Sriracha", new EtatIngredientLiquide(1));
            } catch (IngredientException pe) {
                System.out.println("Erreur dans la quantite d'ingredient" + pe.getMessage());
                Assert.fail();
            }

            // Créer un Épice quantité négative
            System.out.println("TestCréer Epice avec quantité négative : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                sriracha = factory.creer("sriracha", new EtatIngredientSolide(-1));
                Assert.fail();
            } catch (IngredientException pe) {
                System.out.println("Task Failed Successfully");
            }

            // Test l'épice
            System.out.println("TestFactoryEpice : valeur retour GOOD = 'Sriracha'");
            System.out.println(sriracha.getNom());
            Assert.assertEquals("Sriracha", sriracha.getNom());

            System.out.println("TestFactoryEpice : valeur retour GOOD = '1L'");
            System.out.println(sriracha.getQty() + "L");
            Assert.assertEquals(1, sriracha.getQty(), 0.05);

            System.out.println("TestFactoryEpice : valeur retour GOOD = 'Liquide': {'Qty (L)': 1}");
            System.out.println(sriracha.getEtat());
            Assert.assertTrue(sriracha.getEtat() instanceof EtatIngredientLiquide);

            //Test set Nom vers nom null
            System.out.println("TestSetNomNull : valeur retour GOOD = 'Sriracha'");
            sriracha.setNom(null);
            System.out.println(sriracha.getNom());
            Assert.assertEquals("Sriracha", sriracha.getNom());
        }

        @Test
        public static void testCreerLaitier() {
            // Crée une factory de produits laitiers
            CreatorIngredient factory = new ConcreteCreatorLaitier();
            Ingredient lait = null;

            // Crée un produit laitier avec la factory
            try {
                lait = factory.creer("Lait", new EtatIngredientLiquide(4));
            } catch (IngredientException pe) {
                System.out.println("Erreur dans la quantite d'ingredient" + pe.getMessage());
                Assert.fail();
            }

            // Créer un Laitier quantité négative
            System.out.println("TestCréer Epice avec quantité négative : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                lait = factory.creer("Lait", new EtatIngredientSolide(-1));
                Assert.fail();
            } catch (IngredientException pe) {
                System.out.println("Task Failed Successfully");
            }

            // Test le produit laitier
            System.out.println("TestFactoryLaitier : valeur retour GOOD = 'Lait'");
            System.out.println(lait.getNom());
            Assert.assertEquals("Lait", lait.getNom());

            System.out.println("TestFactoryLaitier : valeur retour GOOD = '4L'");
            System.out.println(lait.getQty() + "L");
            Assert.assertEquals(4, lait.getQty(), 0.05);

            System.out.println("TestFactoryLaitier : valeur retour GOOD = ''Liquide': {'Qty (L)': 4}'");
            System.out.println(lait.getEtat());
            Assert.assertTrue(lait.getEtat() instanceof EtatIngredientLiquide);

            //Test set Nom vers nom null
            System.out.println("TestSetNomNull : valeur retour GOOD = 'Lait'");
            lait.setNom(null);
            System.out.println(lait.getNom());
            Assert.assertEquals("Lait", lait.getNom());
        }

        @Test
        public static void testCreerViande() {
            // Crée une factory de viande
            CreatorIngredient factory = new ConcreteCreatorViande();
            Ingredient coteLevee = null;

            // Crée une viande avec la factory
            try {
                coteLevee = factory.creer("coteLevee", new EtatIngredientSolide(0.454));
            } catch (IngredientException pe) {
                System.out.println("Erreur dans la quantite d'ingredient" + pe.getMessage());
                Assert.fail();
            }

            // Créer une Viande quantité négative
            System.out.println("TestCréer Epice avec quantité négative : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                coteLevee = factory.creer("coteLevee", new EtatIngredientSolide(-1));
                Assert.fail();
            } catch (IngredientException pe) {
                System.out.println("Task Failed Successfully");
            }

            // Test la viande
            System.out.println("TestFactoryViande : valeur retour GOOD = 'coteLevee'");
            System.out.println(coteLevee.getNom());
            Assert.assertEquals("coteLevee", coteLevee.getNom());

            System.out.println("TestFactoryViande : valeur retour GOOD = '0.454 kg'");
            System.out.println(coteLevee.getQty() + "kg");
            Assert.assertEquals(0.454, coteLevee.getQty(), 0.05);

            System.out.println("TestFactoryViande : valeur retour GOOD = ''Solide': {'Qty (kg)': 0.454}''");
            System.out.println(coteLevee.getEtat());
            Assert.assertTrue(coteLevee.getEtat() instanceof EtatIngredientSolide);

            //Test set Nom vers nom null
            System.out.println("TestSetNomNull : valeur retour GOOD = 'coteLevee'");
            coteLevee.setNom(null);
            System.out.println(coteLevee.getNom());
            Assert.assertEquals("coteLevee", coteLevee.getNom());

        }
    }

    private static class TestChef {
        Chef Zeff;
        Inventaire congelateur;

        Ingredient tomatoSauce;
        Ingredient pepperoni;
        Ingredient bacon;
        Ingredient cheese;
        Ingredient pate;

        Recette pizzaRecette;
        PlatAuMenu pizzMenu;
        PlatChoisi pizza;
        PlatChoisi pizza2;


        public TestChef() {
            // Crée le chef et récupère l'inventaire
            Zeff = Chef.getInstance();
            Zeff.setNom("Zeff");
            Inventaire.clear();
            congelateur = Inventaire.getInstance();

            // Crée les ingrédients pour une pizza
            try {
                tomatoSauce = new Legume("tomate", new EtatIngredientLiquide(0.5));
                pepperoni = new Viande("pepperoni", new EtatIngredientSolide(0.2));
                bacon = new Viande("bacon", new EtatIngredientSolide(0.2));
                cheese = new Laitier("cheese", new EtatIngredientSolide(1));
                pate = new Fruit("pate", new EtatIngredientSolide(0.454));
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test du Chef " + ie.getMessage());
                Assert.fail();
            }

            // Crée la recette avec les ingrédients
            pizzaRecette = new Recette(new Ingredient[]{pepperoni, tomatoSauce, bacon, cheese, pate});

            // Création d'un nouveau plat au menu avec la recette
            try {
                pizzMenu = new PlatAuMenu(69, "pizza pepperoni bacon", 60);
                pizzMenu.setRecette(pizzaRecette);
            } catch (PlatException pe) {
                System.out.println("Erreur dans le plat au menu" + pe.getMessage());
                Assert.fail();
            }

            // Création d'un plat à l'aide du menu
            try {
                pizza = new PlatChoisi(pizzMenu, 1);
                pizza2 = new PlatChoisi(pizzMenu, 2);   // Il n'y aura pas assez d'ingrédients pour cette pizza.
            } catch (PlatException pe) {
                System.out.println("Erreur dans le plat choisie" + pe.getMessage());
                Assert.fail();
            }

            // Ajout des ingrédiens suffisants pour faire une pizza dans l'inventaire
            try {
                congelateur.ajouterIngredient(new Ingredient[]{pepperoni, tomatoSauce, bacon, cheese, pate});
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test du chef: " + ie.getMessage());
                Assert.fail();
            }
        }

        @Test
        public static void runTests() {
            new TestChef().testNom();
            new TestChef().testCuisiner();
        }

        @Test
        public void testNom() {
            System.out.println("TestChefNom : valeur retour GOOD = 'Zeff'");
            Assert.assertEquals(Zeff.getNom(), "Zeff");
            System.out.println(Zeff.getNom());

            //TestChangement nom null
            Zeff.setNom(null);
            System.out.println("TestChefNom : valeur retour GOOD = 'Zeff'");
            Assert.assertEquals(Zeff.getNom(), "Zeff");
            System.out.println(Zeff.getNom());
        }

        @Test
        public void testCuisiner() {
            System.out.println("TestErrorIngrdient : valeur retour GOOD = 'Quantité d'ingredients insuffisant'");

            // Tentative de cuisiner avec une quantité d'ingrédient insuffisant dans l'inventaire
            // devrait throw une IngredientException.
            try {
                Zeff.cuisiner(pizza2);
                System.out.println("Error avec l'erreur");
                Assert.fail();
            } catch (IngredientException Ie) {
                System.out.println("Quantité d'ingredients insuffisant");
                Assert.assertTrue(pizza2.getEtat() instanceof ErrorServir);
            } catch (PlatException pe) {
                System.out.println("Erreur de Changement d'etat");
                Assert.fail();
            }

            // La tentative de cuisine ne devrait pas avoir consommé d'ingrédients, on peut donc maintenant cuisiner
            // une quantité de plat dont on a les ingrédients en inventaire.
            System.out.println("TestErrorIngrdient : valeur retour GOOD = 'Servi'");
            try {
                Zeff.cuisiner(pizza);
                System.out.println(pizza.getEtat());
                Assert.assertTrue(pizza.getEtat() instanceof Servi);
            } catch (IngredientException Ie) {
                System.out.println("Quantité d'ingredients insuffisant");
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Erreur de Changement d'etat");
                Assert.fail();
            }
        }
    }

    private static class TestRecette {
        Recette recetteSteak;
        Ingredient steak;
        Ingredient sel;
        Ingredient poivre;
        Ingredient tomate;
        Ingredient laitue;

        TestRecette() {
            // Création des ingrédients dans la recette
            try {
                steak = new Viande("Steak", new EtatIngredientSolide(0.3));
                sel = new Epice("Sel", new EtatIngredientSolide(0.02));
                poivre = new Epice("Poivre", new EtatIngredientSolide(0.01));
                tomate = new Legume("Tomate", new EtatIngredientSolide(0.1));
                laitue = new Legume("Laitue", new EtatIngredientSolide(0.05));
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test de Recette: " + ie.getMessage());
                Assert.fail();
            }

            // Création d'une nouvelle recette avec les ingrédients
            recetteSteak = new Recette(new Ingredient[]{steak, sel, poivre});
        }

        @Test
        public static void runTests() {
            new TestRecette().testRecette();
            new TestRecette().testAddIngredient();
            new TestRecette().testSetIngredients();
        }

        @Test
        public void testRecette() {
            System.out.println("Test Recette Steak + Sel/Poivre");
            System.out.println("testRecetteAfficher : valeur retour GOOD = Objet JSON contenant la recette du steak sel et poivre");
            System.out.println(recetteSteak);

            // Vérification que la recette contient les bons ingrédients et les bonnes valeurs
            List<Ingredient> listeIngredient = recetteSteak.getIngredients();
            Assert.assertEquals(steak, listeIngredient.get(0));
            Assert.assertEquals(sel, listeIngredient.get(1));
            Assert.assertEquals(poivre, listeIngredient.get(2));
            Assert.assertEquals(0.3, listeIngredient.get(0).getQty(), 0.005);
            Assert.assertEquals(3, listeIngredient.size());
        }

        @Test
        public void testAddIngredient() {
            // Ajoute une tomate à la fin de la recette
            recetteSteak.addIngredient(tomate);
            System.out.println("Test Recette Ajout de tomate");
            System.out.println(recetteSteak);

            // Vérification que la recette contient maintenant une tomate en dernière position
            Assert.assertEquals(tomate, recetteSteak.getIngredients().get(3));
            Assert.assertEquals(4, recetteSteak.getIngredients().size());
        }

        @Test
        public void testSetIngredients() {
            // Remplace les anciens ingrédients par une nouvelle liste d'ingrédients
            recetteSteak.setIngredients(new Ingredient[]{laitue, tomate, sel});

            System.out.println("Test Recette plat steak végétarien");
            System.out.println(recetteSteak);

            // La nouvelle liste d'ingrédient ne devrait pas contenir de steak ni de poivre
            List<Ingredient> listeIngredient = recetteSteak.getIngredients();
            Assert.assertEquals(laitue, listeIngredient.get(0));
            Assert.assertEquals(3, listeIngredient.size());
            Assert.assertFalse(listeIngredient.contains(steak));
        }
    }

    private static class TestInventaire {
        Inventaire congelo;

        Ingredient tomatoSauce;
        Ingredient pepperoni;
        Ingredient bacon;
        Ingredient cheese;
        Ingredient pate;

        public TestInventaire() {
            // Création de l'inventaire
            Inventaire.clear();
            congelo = Inventaire.getInstance();

            // Création des ingrédients
            try {
                tomatoSauce = new Legume("tomate", new EtatIngredientLiquide(5));
                pepperoni = new Viande("pepperoni", new EtatIngredientSolide(2));
                bacon = new Viande("bacon", new EtatIngredientSolide(1));
                cheese = new Laitier("cheese", new EtatIngredientSolide(6));
                pate = new Fruit("pate", new EtatIngredientSolide(5));
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test de l'Inventaire : " + ie.getMessage());
                Assert.fail();
            }

            // Ajout des ingrédients dans l'inventaire
            try {
                congelo.ajouterIngredient(new Ingredient[]{pepperoni, tomatoSauce, bacon, cheese, pate});
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test de l'Inventaire : " + ie.getMessage());
                Assert.fail();
            }
        }

        @Test
        public static void runTests() {
            new TestInventaire().testInventairePresent();
            new TestInventaire().testAjoutDouble();
            new TestInventaire().testConsommation();
        }

        @Test
        public void testInventairePresent() {
            // Récupération d'un ingrédient existant
            System.out.println("testInventairePrésent : valeur retour GOOD = 'tomate'");
            Ingredient tomate = congelo.getIngredient("tomate");
            Assert.assertNotNull(tomate);
            System.out.println(tomate.getNom());
            Assert.assertEquals(tomatoSauce, tomate);

            // Récupération d'un ingrédient innexistant
            System.out.println("testIngrédientAbsent : valeur retour GOOD = 'null'");
            Ingredient merde = congelo.getIngredient("merde");
            Assert.assertNull(merde);

            // Récupération d'un ingrédient avec une string null
            System.out.println("testNullIngredient : valeur retour GOOD = 'null'");
            Ingredient nullMerde = congelo.getIngredient(null);
            Assert.assertNull(nullMerde);
        }

        @Test
        public void testAjoutDouble() {
            {
                double qtyInitiale = tomatoSauce.getQty();
                // Duplication d'un ingrédient (tomatoSauce est déjà dans le congélateur)
                try {
                    congelo.ajouterIngredient(tomatoSauce);
                } catch (IngredientException ie) {
                    System.out.println("Erreur dans le test de l'Inventaire : " + ie.getMessage());
                    Assert.fail();
                }

                // Récupération de l'ingrédient ajouté (qty initiale = 5, x2 = 10)
                System.out.println("testAjoutDouble : valeur retour GOOD = '10kg'");
                Ingredient tomate = congelo.getIngredient("tomate");
                Assert.assertNotNull(tomate);
                System.out.println(tomate.getQty() + "kg");
                Assert.assertEquals(10, tomate.getQty(), 0.05);

                // Le tomatoSauce hors de l'inventaire ne devrait pas avoir été affecté par l'ajout
                Assert.assertEquals(qtyInitiale, tomatoSauce.getQty(), 0.05);
            }
            {
                // Création d'un ingrédient similaire avec le même nom
                Ingredient tomate2 = null;
                try {
                    tomate2 = new Fruit("tomate", new EtatIngredientLiquide(3));
                } catch (IngredientException ie) {
                    System.out.println("Erreur dans le test de l'Inventaire : " + ie.getMessage());
                    Assert.fail();
                }

                // Ajout du nouvel ingrédient à l'inventaire
                try {
                    congelo.ajouterIngredient(tomate2);
                } catch (IngredientException ie) {
                    System.out.println("Erreur dans le test de l'Inventaire : " + ie.getMessage());
                    Assert.fail();
                }

                // Récupération de l'ingrédient ajouté (qty initiale = 10, + 3 = 13)
                System.out.println("testAjout : valeur retour GOOD = '13kg'");
                Ingredient tomate = congelo.getIngredient("tomate");
                Assert.assertNotNull(tomate);
                System.out.println(tomate.getQty() + "kg");
                Assert.assertEquals(13, tomate.getQty(), 0.05);
            }
            {
                // Ajout d'un ingrédient null à l'inventaire (devrait throw une IngredientException)
                int tailleInitiale = congelo.getIngredientSize();
                System.out.println("testAjoutNull : valeur retour GOOD = 'Impossible de rajouter un ingrédient null à l'inventaire'");
                try {
                    Ingredient nullIngredient = null;
                    congelo.ajouterIngredient(nullIngredient);
                    Assert.fail();      // On devrait avoir throw
                } catch (IngredientException ie) {
                    System.out.println(ie.getMessage());
                    // L'inventaire ne devrait pas avoir plus d'ingrédients
                    Assert.assertEquals(tailleInitiale, congelo.getIngredientSize());
                }
            }
        }

        @Test
        public void testConsommation() {
            // Création d'une recette
            Recette recettePizza = new Recette(new Ingredient[]{pepperoni, tomatoSauce, cheese, bacon, pate});

            // Consommation d'une demi-recette
            try {
                congelo.consommerRecette(recettePizza, 1, 0.5);
            } catch (IngredientException ie) {
                System.out.println("Test Consommation Erreur: " + ie.getMessage());
                Assert.fail();
            }

            //Consommation null
            System.out.println("Test Consommation null : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                congelo.consommerRecette(null, 1, 0.5);
                Assert.fail();
            } catch (IngredientException ie) {
                System.out.println("Task Failed Successfully");
            }

            //Test quantité négative
            System.out.println("Test Quantité négative : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                congelo.consommerRecette(recettePizza, -1, 0.5);
                Assert.fail();
            } catch (IngredientException ie) {
                System.out.println("Task Failed Successfully");
            }

            //Test proportion négative
            System.out.println("Test Proportion négative : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                congelo.consommerRecette(recettePizza, 1, -1);
                Assert.fail();
            } catch (IngredientException ie) {
                System.out.println("Task Failed Successfully");
            }

            //Test proportion trop grande
            System.out.println("Test Proportion > 1 : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                congelo.consommerRecette(recettePizza, 1, 3);
                Assert.fail();
            } catch (IngredientException ie) {
                System.out.println("Task Failed Successfully");
            }

            // Les qty devraient être de moitié
            System.out.println("Test Consommation : valeur retour GOOD = Objet JSON contenant l'inverntaire et la recette suite à la consommation");
            System.out.println(congelo);
            System.out.println(recettePizza);
            Assert.assertEquals(pepperoni.getQty() / 2, congelo.getIngredient(pepperoni.getNom()).getQty(), 0.05);
            Assert.assertEquals(tomatoSauce.getQty() / 2, congelo.getIngredient(tomatoSauce.getNom()).getQty(), 0.05);
            Assert.assertEquals(cheese.getQty() / 2, congelo.getIngredient(cheese.getNom()).getQty(), 0.05);
            Assert.assertEquals(bacon.getQty() / 2, congelo.getIngredient(bacon.getNom()).getQty(), 0.05);
            Assert.assertEquals(pate.getQty() / 2, congelo.getIngredient(pate.getNom()).getQty(), 0.05);

            // Consommation d'une recette complète (devrait échouer)
            System.out.println("Test Consommation : valeur retour GOOD = 'Ingrédients manquants dans l'inventaire'");
            try {
                congelo.consommerRecette(recettePizza, 1, 1);
                System.out.println("Erreur avec l'erreur");
                Assert.fail();
            } catch (IngredientException ie) {
                System.out.println(ie.getMessage());
            }

            // Les qty devraient être encore les mêmes
            System.out.println("Test Consommation : valeur retour GOOD = Objet JSON contenant l'inverntaire et la recette suite à la consommation");
            System.out.println(congelo);
            System.out.println(recettePizza);
            Assert.assertEquals(pepperoni.getQty() / 2, congelo.getIngredient(pepperoni.getNom()).getQty(), 0.05);
            Assert.assertEquals(tomatoSauce.getQty() / 2, congelo.getIngredient(tomatoSauce.getNom()).getQty(), 0.05);
            Assert.assertEquals(cheese.getQty() / 2, congelo.getIngredient(cheese.getNom()).getQty(), 0.05);
            Assert.assertEquals(bacon.getQty() / 2, congelo.getIngredient(bacon.getNom()).getQty(), 0.05);
            Assert.assertEquals(pate.getQty() / 2, congelo.getIngredient(pate.getNom()).getQty(), 0.05);
        }
    }

    private static class TestPlat {
        public static void testPlat() {
            // Création des ingrédients de la recette
            Ingredient poire, cerise, pomme, jus;
            poire = cerise = pomme = jus = null;
            try {
                poire = new Fruit("Poire", new EtatIngredientSolide(0.1));
                cerise = new Fruit("Cerise", new EtatIngredientSolide(0.1));
                pomme = new Fruit("Pomme", new EtatIngredientSolide(0.1));
                jus = new Fruit("Jus", new EtatIngredientLiquide(1));
            } catch (IngredientException ie) {
                System.out.println("TestBuilderPlat: Erreur création d'ingrédient : " + ie.getMessage());
                Assert.fail();
            }

            // Building du plat
            PlatBuilder pb = new PlatBuilder();
            Recette recetteSaladeFruit = null;
            try {
                recetteSaladeFruit = new Recette(new Ingredient[]{poire, cerise, pomme, jus});
                pb.buildDescription("Salade de fruit")
                        .buildPrix(12.50)
                        .buildRecette(recetteSaladeFruit);
            } catch (PlatException pe) {
                System.out.println("TestBuilderPlat : " + pe.getMessage());
                Assert.fail();
            }
            PlatAuMenu saladeFruit = pb.getResult();

            // Vérification du plat construit
            System.out.println("TestPlatBuilder : valeur retour GOOD = 'Salade de fruit'");
            System.out.println(saladeFruit.getDescription());
            Assert.assertEquals("Salade de fruit", saladeFruit.getDescription());

            System.out.println("TestPlatBuilder : valeur retour GOOD = '12.5'");
            System.out.println(saladeFruit.getPrix());
            Assert.assertEquals(12.5, saladeFruit.getPrix(), 0.5);

            System.out.println("TestPlatBuilder : String JSON représentant la recette");
            System.out.println(saladeFruit.getRecette());
            Assert.assertEquals(recetteSaladeFruit, saladeFruit.getRecette());

            // Test avec une recette null
            try {
                Recette nullRecette = null;
                pb.buildRecette(nullRecette);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task failed successfully");
            }

            // La recette ne devrait pas avoir changée
            PlatAuMenu saladeFruit2 = pb.getResult();
            System.out.println("TestPlatBuilder : String JSON représentant la recette");
            System.out.println(saladeFruit.getRecette());
            Assert.assertEquals(saladeFruit, saladeFruit2);


            // Test du builder de plat enfant
            PlatEnfantBuilder peb = new PlatEnfantBuilder();
            try {
                peb.buildProportion(0.5)
                        .buildDescription("Salade de fruit pour enfants")
                        .buildPrix(10.75)
                        .buildRecette(recetteSaladeFruit);
            } catch (PlatException pe) {
                System.out.println("TestBuilderPlatEnfant Erreur: " + pe.getMessage());
                Assert.fail();
            }

            PlatEnfant saladeFruitEnfant = peb.getResult();

            // Test de proportion négative & > 1, ne devrait pas changer les résultats de la validation
            System.out.println("TestPlatEnfant setProportion < 0 : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                saladeFruitEnfant.setProportion(-4);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
            }

            System.out.println("TestPlatEnfant setProportion > 1 : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                saladeFruitEnfant.setProportion(7);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
            }

            // Vérification du plat construit
            System.out.println("TestPlatEnfantBuilder : valeur retour GOOD = 'Salade de fruit pour enfants'");
            System.out.println(saladeFruitEnfant.getDescription());
            Assert.assertEquals("Salade de fruit pour enfants", saladeFruitEnfant.getDescription());

            System.out.println("TestPlatEnfantBuilder : valeur retour GOOD = '10.75'");
            System.out.println(saladeFruitEnfant.getPrix());
            Assert.assertEquals(10.75, saladeFruitEnfant.getPrix(), 0.5);

            System.out.println("TestPlatEnfantBuilder: valeur retour GOOD = '0.5'");
            System.out.println(saladeFruitEnfant.getProportion());
            Assert.assertEquals(0.5, saladeFruitEnfant.getProportion(), 0.05);

            System.out.println("TestPlatEnfantBuilder : String JSON représentant la recette");
            System.out.println(saladeFruitEnfant.getRecette());
            Assert.assertEquals(recetteSaladeFruit, saladeFruitEnfant.getRecette());

            // Test du builder de plat santé
            PlatSanteBuilder psb = new PlatSanteBuilder();
            try {
                psb.buildKCal(0.65)
                        .buildGras(0.05)
                        .buildChol(0)
                        .buildDescription("Salade de fruit")
                        .buildPrix(12.50)
                        .buildRecette(recetteSaladeFruit);
            } catch (PlatException pe) {
                System.out.println("TestBuilderPlatSanté Erreur: " + pe.getMessage());
                Assert.fail();
            }

            PlatSante saladeFruitSante = psb.getResult();

            // Test de valeurs impossibles, ne devrait pas affecter les résultats de la vérification
            System.out.println("TestPlatSanté setKcal négatif : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                saladeFruitSante.setKcal(-3.1415926535897953);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
            }

            System.out.println("TestPlatSanté setGras négatif : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                saladeFruitSante.setGras(-120.8);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
            }

            System.out.println("TestPlatSanté setChol négatif : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                saladeFruitSante.setChol(-65537);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
            }

            System.out.println("TestPlatSanté setRecette null : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                saladeFruitSante.setRecette(null);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
            }

            System.out.println("TestPlatSanté setPrix négatif : valeur retour GOOD = 'Task Failed Successfully'");
            try {
                saladeFruitSante.setPrix(-0.00000001);
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println("Task Failed Successfully");
            }

            // Vérification du plat construit
            System.out.println("TestPlatSantéBuilder : valeur retour GOOD = 'Salade de fruit'");
            System.out.println(saladeFruitSante.getDescription());
            Assert.assertEquals("Salade de fruit", saladeFruitSante.getDescription());

            System.out.println("TestPlatSantéBuilder : valeur retour GOOD = '12.5'");
            System.out.println(saladeFruitSante.getPrix());
            Assert.assertEquals(12.5, saladeFruitSante.getPrix(), 0.005);

            System.out.println("TestPlatSantéBuilder : valeur retour GOOD = '0.65'");
            System.out.println(saladeFruitSante.getKcal());
            Assert.assertEquals(0.65, saladeFruitSante.getKcal(), 0.005);

            System.out.println("TestPlatSantéBuilder : valeur retour GOOD = '0.05'");
            System.out.println(saladeFruitSante.getGras());
            Assert.assertEquals(0.05, saladeFruitSante.getGras(), 0.005);

            System.out.println("TestPlatSantéBuilder : valeur retour GOOD = '0'");
            System.out.println(saladeFruitSante.getChol());
            Assert.assertEquals(0, saladeFruitSante.getChol(), 0.005);

            System.out.println("TestPlatSantéBuilder : String JSON représentant la recette");
            System.out.println(saladeFruitSante.getRecette());
            Assert.assertEquals(recetteSaladeFruit, saladeFruitSante.getRecette());


            // Test de la fonctionalité de copie
            PlatAuMenu saladeFruit3 = saladeFruit.makeCopy();
            try {
                saladeFruit.setDescription("Salade de fruit modifiee");
                saladeFruit.setPrix(0);
            } catch (PlatException pe) {
                System.out.println("Erreur dans la copie de plats: " + pe.getMessage());
                Assert.fail();
            }

            // Vérification des valeurs, elles devraient toutes être différentes
            System.out.println("TestPlatCopy : valeur retour GOOD = 'Salade de fruit'");
            System.out.println(saladeFruit3.getDescription());
            Assert.assertEquals("Salade de fruit", saladeFruit3.getDescription());
            Assert.assertNotEquals(saladeFruit.getDescription(), saladeFruit3.getDescription());

            System.out.println("TestPlatCopy : valeur retour GOOD = '12.5'");
            System.out.println(saladeFruit3.getPrix());
            Assert.assertEquals(12.5, saladeFruit3.getPrix(), 0.5);
            Assert.assertNotEquals(saladeFruit.getPrix(), saladeFruit3.getPrix());
        }

        public static void runTests() {
            TestPlat.testPlat();
        }
    }

    private static class TestClient {
        Client nouveauClient;

        public TestClient() {
            try {
                nouveauClient = new Client(2, "Pascal", "5258935034607288");
            } catch (FactureException fe) {
                System.out.println("Erreur dans la création du client: " + fe.getMessage());
            }
        }

        public static void runTests() {
            TestClient testClient = new TestClient();
            testClient.testIdClient();
            testClient.testNomClient();
            testClient.testNoCarteCredit();
        }

        @Test
        public void testIdClient() {
            System.out.println("TestIdClient : valeur retour GOOD = '2'");
            System.out.println(nouveauClient.getIdClient());
            Assert.assertEquals(2, nouveauClient.getIdClient());


            System.out.println("TestIdClient : valeur retour GOOD = '0'");
            nouveauClient.setIdClient(0);
            System.out.println(nouveauClient.getIdClient());
            Assert.assertEquals(0, nouveauClient.getIdClient());


            System.out.println("TestIdClient : valeur retour GOOD = '-5'");
            nouveauClient.setIdClient(-5);
            System.out.println(nouveauClient.getIdClient());
            Assert.assertEquals(-5, nouveauClient.getIdClient());
        }

        @Test
        public void testNomClient() {
            System.out.println("TestIdClient : valeur retour GOOD = 'Pascal'");
            System.out.println(nouveauClient.getNom());
            Assert.assertEquals("Pascal", nouveauClient.getNom());

            System.out.println("TestIdClient : valeur retour GOOD = 'Pascal'");
            nouveauClient.setNom(null);
            System.out.println(nouveauClient.getNom());
            Assert.assertEquals("Pascal", nouveauClient.getNom());
        }

        @Test
        public void testNoCarteCredit() {
            System.out.println("TestIdClient : valeur retour GOOD = '5258935034607288'");
            System.out.println(nouveauClient.getNumeroCarteCredit());
            Assert.assertEquals("5258935034607288", nouveauClient.getNumeroCarteCredit());

            System.out.println("TestIdClient : valeur retour GOOD = '0000-0000-0000-0000'");
            try {
                nouveauClient.setNumeroCarteCredit("0000-0000-0000-0000");
            } catch (FactureException fe) {
                System.out.println("Erreur dans le # de carte de crédit: " + fe.getMessage());
                Assert.fail();
            }
            System.out.println(nouveauClient.getNumeroCarteCredit());
            Assert.assertEquals("0000-0000-0000-0000", nouveauClient.getNumeroCarteCredit());

            //passer un numéro null
            System.out.println("TestSet#CarteNUll : valeur retour GOOD = 'Task failed successfully'");
            try {
                nouveauClient.setNumeroCarteCredit(null);
                System.out.println("Error dans L'Erreur");
                Assert.fail();
            } catch (FactureException fe) {
                System.out.println("Task failed successfully");
            }
            //passer un numéro faux avec des lettres
            System.out.println("TestSetMauvaisNumeros : valeur retour GOOD = 'Task failed successfully'");
            try {
                nouveauClient.setNumeroCarteCredit("fasdfasdasfdsGVSD 1213123 12 3123");
                System.out.println("Error dans L'Erreur");
                Assert.fail();
            } catch (FactureException fe) {
                System.out.println("Task failed successfully");
            }
            //passer un numéro faux sans le bon nombre de chiffres
            System.out.println("TestSetPasAssezDeChiffre : valeur retour GOOD = 'Task failed successfully'");
            try {
                nouveauClient.setNumeroCarteCredit("1234");
                System.out.println("Error dans L'Erreur");
                Assert.fail();
            } catch (FactureException fe) {
                System.out.println("Task failed successfully");
            }
            //passer un numéro faux sans le bon nombre de chiffres
            System.out.println("TestSetTropDeChiffre : valeur retour GOOD = 'Task failed successfully'");
            try {
                nouveauClient.setNumeroCarteCredit("1234 1566 1566 5165 6541");
                System.out.println("Error dans L'Erreur");
                Assert.fail();
            } catch (FactureException fe) {
                System.out.println("Task failed successfully");
            }

        }
    }

    private static class TestMenuFact {
        Chef zeff;
        Client luffy;
        Menu menu;
        Inventaire congelateur;
        Facture facture;

        public TestMenuFact() {
            testCreationClient();
            testCreationChef();
            testCreationMenu();
            testCreationInventaire();
            testCreationFacture();
        }

        public static void runTests() {
            TestMenuFact testMenuFact = new TestMenuFact();
            testMenuFact.testPlatCorrect();
            testMenuFact.testAjoutMenu();
            testMenuFact.testPlatSante();
            testMenuFact.testPlatEnfant();
            testMenuFact.testPlatInnexistant();
            testMenuFact.testPlatInsuffisant();
        }

        @Test
        public void testCreationClient() {
            try {
                luffy = new Client(1, "Luffy", "0123 4567 8901 2345");
            } catch (FactureException fe) {
                System.out.println("Erreur dans la création du client" + fe.getMessage());
                Assert.fail();
            }

            System.out.println(); // TODO Valeur client
            Assert.assertEquals(1, luffy.getIdClient());
            Assert.assertEquals("Luffy", luffy.getNom());
            Assert.assertEquals("0123 4567 8901 2345", luffy.getNumeroCarteCredit());
        }

        @Test
        public void testCreationChef() {
            zeff = Chef.getInstance();
            zeff.setNom("Zeff");

            System.out.println("Chef: {\n\tNom: 'Zeff'\n\n}");
            System.out.println(zeff);
            Assert.assertEquals("Chef: {\n\tNom: 'Zeff'\n\n}", zeff.toString());
        }

        @Test
        public void testCreationMenu() {
            menu = Menu.getInstance();
            menu.setDescription("Menu MenuFact");

            System.out.println("TestErrorIngrdient : valeur retour GOOD = 'Menu MenuFact'");
            System.out.println(menu.getDescription());
            Assert.assertEquals("Menu MenuFact", menu.getDescription());

            Ingredient os, viandeHachee, gomuFruit, pateTarte, poisson, jusCitron;
            os = viandeHachee = gomuFruit = pateTarte = poisson = jusCitron = null;

            try {
                os = new Viande("Os", new EtatIngredientSolide(0.2));
                viandeHachee = new Viande("Viande Hachee", new EtatIngredientSolide(0.5));

                gomuFruit = new Fruit("Gomu Gomu", new EtatIngredientSolide(0.4));
                pateTarte = new Fruit("Pâte à tarte", new EtatIngredientSolide(0.7));

                poisson = new Viande("Poisson", new EtatIngredientSolide(0.3));
                jusCitron = new Fruit("Jus de citron", new EtatIngredientLiquide(0.075));


            } catch (IngredientException ie) {
                System.out.println("Erreur dans la création des ingrédients au menu: " + ie.getMessage());
            }

            Recette recetteOsViande = new Recette(new Ingredient[]{os, viandeHachee});
            Recette recetteTarteGomuFruit = new Recette(new Ingredient[]{gomuFruit, pateTarte});
            Recette recettePoisson = new Recette(new Ingredient[]{poisson, jusCitron});


            PlatBuilder pb = new PlatBuilder();

            try {
                pb.buildPrix(24.99)
                        .buildDescription("Viande hachée autour d'un os")
                        .buildRecette(recetteOsViande);
            } catch (PlatException pe) {
                System.out.println("Erreur dans la création du plat: " + pe.getMessage());
            }
            PlatAuMenu platOsViande = pb.getResult();

            PlatEnfantBuilder peb = new PlatEnfantBuilder();
            try {
                peb.buildProportion(0.25)
                        .buildPrix(18.99)
                        .buildDescription("Tarte au devil fruit")
                        .buildRecette(recetteTarteGomuFruit);
            } catch (PlatException pe) {
                System.out.println("Erreur dans la création du plat enfant: " + pe.getMessage());
            }
            PlatEnfant tarteGomuFruit = peb.getResult();

            PlatSanteBuilder psb = new PlatSanteBuilder();
            try {
                psb.buildKCal(0.54)
                        .buildChol(0.001)
                        .buildGras(0.04)
                        .buildPrix(16.99)
                        .buildDescription("Saumon au four")
                        .buildRecette(recettePoisson);
            } catch (PlatException pe) {
                System.out.println("Erreur dans la création du plat enfant: " + pe.getMessage());
            }
            PlatSante platPoisson = psb.getResult();

            menu.ajoute(platOsViande);      // 0
            menu.ajoute(tarteGomuFruit);    // 1
            menu.ajoute(platPoisson);       // 2

            System.out.println(); // TODO Valeur menu
            System.out.println(menu);

            menu.ajoute(null);

            System.out.println(); // TODO Valeur menu
            System.out.println(menu);
        }

        @Test
        public void testCreationInventaire() {
            Ingredient tomatoSauce, pepperoni, bacon, cheese, pate, os, viandeHachee, gomuFruit, pateTarte, poisson, jusCitron, curcuma, poire, cerise, pomme, jus, steak, sel, poivre, tomate, laitue, kobeSteak, chipsLays, spaghetti, meetBall;
            tomatoSauce = pepperoni = bacon = cheese = pate = os = viandeHachee = gomuFruit = pateTarte = poisson = jusCitron = curcuma = poire = cerise = pomme = jus = sel = poivre = laitue = tomate = steak = kobeSteak = chipsLays = meetBall = spaghetti = null;

            congelateur = Inventaire.getInstance();

            // Création des ingrédients
            try {
                curcuma = new Epice("curcuma", new EtatIngredientSolide(1));
                tomatoSauce = new Legume("tomate", new EtatIngredientLiquide(500));
                pepperoni = new Viande("pepperoni", new EtatIngredientSolide(10));
                bacon = new Viande("bacon", new EtatIngredientSolide(5));
                cheese = new Laitier("cheese", new EtatIngredientSolide(50));
                pate = new Fruit("pate", new EtatIngredientSolide(50));
                os = new Viande("Os", new EtatIngredientSolide(1));
                viandeHachee = new Viande("Viande Hachee", new EtatIngredientSolide(30));
                poire = new Fruit("Poire", new EtatIngredientSolide(1));
                cerise = new Fruit("Cerise", new EtatIngredientSolide(1));
                pomme = new Fruit("Pomme", new EtatIngredientSolide(1));
                jus = new Fruit("Jus", new EtatIngredientLiquide(5));
                gomuFruit = new Fruit("Gomu Gomu", new EtatIngredientSolide(10));
                pateTarte = new Fruit("Pâte à tarte", new EtatIngredientSolide(20));
                steak = new Viande("Steak", new EtatIngredientSolide(50));
                sel = new Epice("Sel", new EtatIngredientSolide(1));
                poivre = new Epice("Poivre", new EtatIngredientSolide(1));
                tomate = new Legume("Tomate", new EtatIngredientSolide(5));
                laitue = new Legume("Laitue", new EtatIngredientSolide(5));
                chipsLays = new Legume("lays", new EtatIngredientGazeux(100));
                spaghetti = new Legume("spaghetti", new EtatIngredientSolide(10));
                meetBall = new Viande("steakHache", new EtatIngredientSolide(10));
                poisson = new Viande("Poisson", new EtatIngredientSolide(10));
                jusCitron = new Fruit("Jus de citron", new EtatIngredientLiquide(1));
                kobeSteak = new Viande("kobe", new EtatIngredientSolide(3));

            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test de l'Inventaire : " + ie.getMessage());
                Assert.fail();
            }

            // Ajout des ingrédients dans l'inventaire
            try {
                congelateur.ajouterIngredient(new Ingredient[]{pepperoni, tomatoSauce, bacon, cheese, pate, os, viandeHachee, gomuFruit, pateTarte, poisson, spaghetti, meetBall, jusCitron, chipsLays, kobeSteak, curcuma, pomme, jus, poire, cerise, sel, poivre, steak, tomate, laitue});
            } catch (IngredientException ie) {
                System.out.println("Erreur dans le test de l'Inventaire : " + ie.getMessage());
                Assert.fail();
            }
            System.out.println(congelateur);
        }

        @Test
        public void testCreationFacture() {
            facture = new Facture("factureTest");

            System.out.println("TestAjoutMenu : valeur retour GOOD = 'La facture ne peut pas être reouverte'");
            try {
                facture.ouvrir();
                System.out.println("Error dans l'erreur");
                Assert.fail();
            } catch (FactureException fe) {
                System.out.println(fe.getMessage());
                System.out.println("Task Failed Successfully");
            }
            try {
                facture.fermer();
                Assert.assertTrue(facture.getEtat() instanceof FactureEtatFermee);
            } catch (FactureException fe) {
                System.out.println("Erreur dans la fermeture de facture: " + fe.getMessage());
                Assert.fail();
            }
            try {
                facture.ouvrir();
                Assert.assertTrue(facture.getEtat() instanceof FactureEtatOuverte);
            } catch (FactureException fe) {
                System.out.println("Erreur dans l'ouverture de facture: " + fe.getMessage());
                Assert.fail();
            }

            facture.associerClient(luffy);
            facture.associerChef(zeff);
        }

        @Test
        public void testAjoutMenu() {
            Ingredient tomatoSauce, pepperoni, bacon, cheese, patePizza;
            tomatoSauce = pepperoni = bacon = cheese = patePizza = null;
            System.out.println("TestAjoutMenu : valeur retour GOOD = Object JSON contenant le Menu");
            try {
                tomatoSauce = new Legume("tomate", new EtatIngredientLiquide(0.5));
                pepperoni = new Viande("pepperoni", new EtatIngredientSolide(0.2));
                bacon = new Viande("bacon", new EtatIngredientSolide(0.2));
                cheese = new Laitier("cheese", new EtatIngredientSolide(1));
                patePizza = new Fruit("pate", new EtatIngredientSolide(0.454));
            } catch (IngredientException ie) {
                System.out.println("Erreur dans la création des ingrédients au menu: " + ie.getMessage());
                Assert.fail();
            }

            Recette recettePizza = new Recette(new Ingredient[]{tomatoSauce, pepperoni, bacon, cheese, patePizza});

            PlatBuilder pb = new PlatBuilder();
            try {
                pb.buildPrix(16.99)
                        .buildDescription("Pizza au bacon")
                        .buildRecette(recettePizza);
            } catch (PlatException pe) {
                System.out.println("Erreur dans la création du plat: " + pe.getMessage());
                Assert.fail();
            }
            PlatAuMenu platPizza = pb.getResult();

            menu.ajoute(platPizza);     // 3

            System.out.println(menu);
        }

        @Test
        public void testPlatCorrect() {
            PlatChoisi plat = null;
            menu.position(0);
            System.out.println("TestPlatCorrect : valeur retour GOOD = 'Servi'");
            try {
                plat = new PlatChoisi(menu.platCourant(), 3);

            } catch (PlatException pe) {
                System.out.println("Erreur dans le test de la facture : " + pe.getMessage());
                Assert.fail();
            }
            Assert.assertFalse(plat.getPlat() instanceof PlatSante || plat.getPlat() instanceof PlatEnfant);
            try {
                facture.ajoutePlat(plat);
            } catch (FactureException | PlatException fe) {
                System.out.println("Erreur dans l'ajout du plat: " + fe.getMessage());
                Assert.fail();
            }
            System.out.println(facture);
            System.out.println(plat.getEtat());
            Assert.assertTrue(plat.getEtat() instanceof Servi);
        }

        @Test
        public void testPlatSante() {
            PlatChoisi plat = null;
            menu.position(2);
            System.out.println("TestPlatSante : valeur retour GOOD = 'Servi'");
            try {
                plat = new PlatChoisi(menu.platCourant(), 3);

            } catch (PlatException pe) {
                System.out.println("Erreur dans le test de la facture : " + pe.getMessage());
                Assert.fail();
            }
            Assert.assertTrue(plat.getPlat() instanceof PlatSante);
            try {
                facture.ajoutePlat(plat);
            } catch (FactureException | PlatException fe) {
                System.out.println("Erreur dans l'ajout du plat: " + fe.getMessage());
                Assert.fail();
            }
            System.out.println(facture);
            System.out.println(plat.getEtat());
            Assert.assertTrue(plat.getEtat() instanceof Servi);
        }

        @Test
        public void testPlatEnfant() {
            PlatChoisi plat = null;
            menu.position(1);
            System.out.println("TestPlatEnfant : valeur retour GOOD = 'Servi'");
            try {
                plat = new PlatChoisi(menu.platCourant(), 3);

            } catch (PlatException pe) {
                System.out.println("Erreur dans le test de la facture : " + pe.getMessage());
                Assert.fail();
            }
        }

        @Test
        public void testPlatInnexistant() {
            String factureOriginale = facture.toString();

            System.out.println("TestErrorIngrdient : valeur retour GOOD = 'Impossible de rajoputer un plat null à la facture'");
            try {
                facture.ajoutePlat(null);
                System.out.println("Erreur dans l'erreur");
                Assert.fail();
            } catch (PlatException pe) {
                System.out.println(pe.getMessage());
            } catch (FactureException fe) {
                System.out.println(fe.getMessage());
                Assert.fail();
            }

            System.out.println("Valeur Good: " + factureOriginale);
            System.out.println(facture);
            Assert.assertEquals(factureOriginale, facture.toString());
        }

        @Test
        public void testPlatInsuffisant() {
            String factureOriginale = facture.toString();

            menu.position(0);
            PlatAuMenu osViande = menu.platCourant();

            System.out.println("TestErrorPlatInsuffisant: valeur retour GOOD = 'Viande hachée autour d'un os'");
            System.out.println(osViande.getDescription());
            Assert.assertEquals("Viande hachée autour d'un os", osViande.getDescription());
            Assert.assertFalse(osViande instanceof PlatSante);
            Assert.assertFalse(osViande instanceof PlatEnfant);

            PlatChoisi osViandeChoisi = null;
            try {
                osViandeChoisi = new PlatChoisi(osViande, 10000);
            } catch (PlatException pe) {
                System.out.println(pe.getMessage());
                Assert.fail();
            }

            System.out.println("TestErrorIngrdient : valeur retour GOOD = 'Il n'y a pas assez d'ingrédient'");
            try {
                facture.ajoutePlat(osViandeChoisi);
            } catch (FactureException | PlatException ex) {
                System.out.println(ex.getMessage());
                Assert.fail();
            }

            System.out.println("Valeur Good: " + factureOriginale);
            System.out.println(facture);
            Assert.assertEquals(factureOriginale, facture.toString());
        }
    }

    @Test
    private static void test0_base() {
        boolean trace = true;
        Assert.assertTrue(trace);

        TestMenuFact02 t = new TestMenuFact02();

        // Création de plats vides
        PlatAuMenu p1, p2, p3, p4, p5;
        PlatSante ps1, ps2, ps3, ps4, ps5;
        p1 = p2 = p3 = p4 = p5 = ps1 = ps2 = ps3 = ps4 = ps5 = null;

        // Initialisation des plats
        try {
            p1 = new PlatAuMenu(0, "PlatAuMenu0", 10);
            p2 = new PlatAuMenu(1, "PlatAuMenu1", 20);
            p3 = new PlatAuMenu(2, "PlatAuMenu2", 30);
            p4 = new PlatAuMenu(3, "PlatAuMenu3", 40);
            p5 = new PlatAuMenu(4, "PlatAuMenu4", 50);


            ps1 = new PlatSante(10, "PlatSante0", 10, 11, 11, 11);
            ps2 = new PlatSante(11, "PlatSante1", 20, 11, 11, 11);
            ps3 = new PlatSante(12, "PlatSante2", 30, 11, 11, 11);
            ps4 = new PlatSante(13, "PlatSante3", 40, 11, 11, 11);
            ps5 = new PlatSante(14, "PlatSante4", 50, 11, 11, 11);
        } catch (PlatException pe) {
            Assert.fail();
        }


        Menu m1 = Menu.getInstance();
        Menu m2 = Menu.getInstance();
        m1.setDescription("menuFact menu");

        Facture f1 = new Facture("Ma facture");
        Client c1 = null;
        try {
            c1 = new Client(1, "Mr Client", "5555 5555 5555 4444");
        } catch (FactureException fe) {
            System.out.println("Erreur de carte de crédit client: " + fe.getMessage());
        }


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
        } catch (FactureException | PlatException ex) {
            System.out.println(ex.getMessage());
        }


        t.test8_AjouterClientFacture(f1, c1);

        try {
            t.test8_AjouterPlatsFacture(f1, m1, 1);
        } catch (FactureException | PlatException ex) {
            System.out.println(ex.getMessage());
        } catch (MenuException me) {
            System.out.println(me);
        }

        try {
            t.test9_PayerFacture(f1);
        } catch (FactureException fe) {
            System.out.println(fe.getMessage());
        }

        try {
            t.test8_AjouterPlatsFacture(f1, m1, 1);
        } catch (FactureException | PlatException ex) {
            System.out.println(ex.getMessage());
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

    @Test
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

    @Test
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

    @Test
    private static void test3_AjoutMenu(boolean trace, Menu m1, Menu m2) {
        System.out.println("=== test3_AjoutMenu");

        if (trace) {
            System.out.println(m1);
            System.out.println(m2);
        }
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    private void test7_CreerFacture(Facture f1, Menu m1) throws FactureException, PlatException {
        System.out.println("===test7_CreerFacture");

        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(), 5);
        f1.ajoutePlat(platChoisi);
        System.out.println(f1);
    }

    @Test
    private void test8_AjouterClientFacture(Facture f1, Client c1) {
        System.out.println("===test8_AjouterClientFacture");
        f1.associerClient(c1);
        System.out.println(f1);
    }

    @Test
    private void test8_AjouterPlatsFacture(Facture f1, Menu m1, int pos) throws MenuException, FactureException, PlatException {
        System.out.println("===test8_AjouterPlatsFacture");

        for (int i = 0; i < pos; i++)
            m1.positionSuivante();

        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(), 5);

        f1.ajoutePlat(platChoisi);
        System.out.println(f1);
    }

    @Test
    private void test9_PayerFacture(Facture f1) throws FactureException {
        System.out.println("===test9_PayerFacture");

        System.out.println("Avant payer la facture");
        System.out.println(f1);

        f1.payer();
        System.out.println("Apres avoir paye la facture");
        System.out.println(f1);
    }
}