package menufact.ingredients;

public class ConcreteCreatorViande implements CreatorIngredient {
    public Ingredient creer(String nom, EtatIngredient etat) {
        return new Viande(nom, etat);
    }
}
