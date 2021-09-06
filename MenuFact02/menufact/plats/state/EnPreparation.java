package menufact.plats.state;

public class EnPreparation implements CommandeEtat
{
    @Override
    public boolean changerEtat(CommandeEtat etat){
        return etat instanceof Servi;
    };
}
