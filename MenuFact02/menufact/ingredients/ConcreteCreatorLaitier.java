package menufact.ingredients;

public class ConcreteCreatorLaitier implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Laitier(nom, etat);
    }
}
