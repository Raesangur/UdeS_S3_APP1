package menufact.facture;

public class Commander {
    public boolean changerEtat(CommandeEtat etat){
        return etat instanceof EnPreparation || etat instanceof ErrorServir;
    };
    public boolean IsOuvert(){
        return true;
    };
}
