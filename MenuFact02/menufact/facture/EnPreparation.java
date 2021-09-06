package menufact.facture;

public class EnPreparation {
    public boolean changerEtat(CommandeEtat etat){
        return etat instanceof Servi;
    };
    public boolean IsOuvert(){
        return true;
    };
}
