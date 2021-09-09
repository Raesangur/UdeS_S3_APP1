package menufact.exceptions;

/**
 * Représente une exception ou un cas d'erreur lié au menu.
 * Utilisé lorsqu'on cherche à changer vers une position invalide.
 */
public class MenuException extends Exception{

    public MenuException(String message){
        super("MenuException: " + message);
    }
}

