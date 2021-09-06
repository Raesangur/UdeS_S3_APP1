package menufact.plats.state;

public class ErrorServir implements CommandeEtat{
    @Override
    public boolean changerEtat(CommandeEtat etat){
        return false;
    };
}
