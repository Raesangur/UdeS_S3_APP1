package menufact.facture;

public class Servi {
    public boolean changerEtat(CommandeEtat etat){
        return etat instanceof Terminer;
    };
    public boolean IsOuvert(){
        return true;
    };
}
