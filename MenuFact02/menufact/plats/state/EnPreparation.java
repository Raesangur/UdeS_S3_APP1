package menufact.plats.state;

public class EnPreparation implements CommandeEtat{
    public boolean changerEtat(CommandeEtat etat){
        return etat instanceof Servi;
    };
}
