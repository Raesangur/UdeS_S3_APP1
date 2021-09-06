package menufact.ingredients;

public class ConcreteCreatorLegume implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Legume(nom, etat);
    }
}
