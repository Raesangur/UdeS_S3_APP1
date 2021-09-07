package menufact;

import menufact.exceptions.MenuException;
import menufact.plats.builder.PlatBuilder;
import menufact.plats.exception.PlatException;
import menufact.facture.exceptions.FactureException;
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

    public static void main(String[] args) throws FactureException {
        // Les tests de base qui venaient avec le code fourni
        test0_base();

        TestIngredient.runTests();
        TestPlatChoisi.runTests();
        TestFactoryIngredient.runTests();
        TestChef.runTests();
        TestRecette.runTests();
        TestInventaire.runTests();
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
            try{
                merde = new Legume("vide", null);
                System.out.println("Error dans L'Erreur");
                Assert.fail();
            }
            catch (IngredientException ie){
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
            System.out.println("===Test Recette Steak + Sel/Poivre");
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
            System.out.println("===Test Recette Ajout de tomate");
            System.out.println(recetteSteak);

            // Vérification que la recette contient maintenant une tomate en dernière position
            Assert.assertEquals(tomate, recetteSteak.getIngredients().get(3));
            Assert.assertEquals(4, recetteSteak.getIngredients().size());
        }

        @Test
        public void testSetIngredients() {
            // Remplace les anciens ingrédients par une nouvelle liste d'ingrédients
            recetteSteak.setIngredients(new Ingredient[]{laitue, tomate, sel});

            System.out.println("===Test Recette plat steak végétarien");
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
            new TestInventaire().testInventairePrésent();
            new TestInventaire().testAjoutDouble();
            new TestInventaire().testConsommation();
        }

        @Test
        public void testInventairePrésent() {
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

    private static class TestBuilderPlat {
        public static void testBuilderPlat() {

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
            try {
                pb.buildDescription("Salade de fruit")
                        .buildPrix(12.50)
                        .buildRecette(new Ingredient[]{poire, cerise, pomme, jus});
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
        }
    }
    // TODO Test Facture?
    // TODO Test Menu?

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

    private void test7_CreerFacture(Facture f1, Menu m1) throws FactureException, PlatException {
        System.out.println("===test7_CreerFacture");

        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(), 5);
        f1.ajoutePlat(platChoisi);
        System.out.println(f1);
    }


    private void test8_AjouterClientFacture(Facture f1, Client c1) {
        System.out.println("===test8_AjouterClientFacture");
        f1.associerClient(c1);
        System.out.println(f1);
    }

    private void test8_AjouterPlatsFacture(Facture f1, Menu m1, int pos) throws MenuException, FactureException, PlatException {
        System.out.println("===test8_AjouterPlatsFacture");

        for (int i = 0; i < pos; i++)
            m1.positionSuivante();

        PlatChoisi platChoisi = new PlatChoisi(m1.platCourant(), 5);

        f1.ajoutePlat(platChoisi);
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
