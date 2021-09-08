package menufact.facture.exceptions;

/**
 * Représente une exception ou un cas d'erreur lié à la facture.
 * Utilisé lors de changement d'états invalides ou d'action durant un état de facture invalide,
 * pour des numéros de carte de crédit invalides,
 * en cas d'absence de chef ou de client sur la facture.
 */
public class FactureException extends Exception {
    public FactureException(String message) {
        super("FactureException: " + message);
    }
}
