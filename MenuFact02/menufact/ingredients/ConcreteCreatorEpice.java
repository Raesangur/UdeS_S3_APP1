package menufact.ingredients;

public class ConcreteCreatorEpice implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Epice(nom, etat);
    }
}
