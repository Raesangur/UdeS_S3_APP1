package menufact.ingredients;

public class ConcreteCreatorFruit implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Fruit(nom, etat);
    }
}
