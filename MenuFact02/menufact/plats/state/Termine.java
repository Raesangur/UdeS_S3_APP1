package menufact.plats.state;

public class Termine implements CommandeEtat{
    @Override
    public boolean changerEtat(CommandeEtat etat){
        return false;
    };
}
