package menufact.plats.exception;

/**
 * Représente une exception ou un cas d'erreur lié aux plats.
 * Utilisé si on configure un prix, des informations de santé ou une proportion négative, ou si la proportion n'est pas valide.
 * Utilisé lorsqu'on essaye de configurer une recette <code>null</code>.
 * Utilisé lors d'une tentative de transition impossible de la state machine du plat.
 */
public class PlatException extends Throwable {
    public PlatException(String message){
        super("PlatException: " + message);
    }
}
