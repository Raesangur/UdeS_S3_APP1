package menufact.plats.exception;

public class PlatException extends Throwable {
    public PlatException(String message){
        super("PlatException: " + message);
    }
}
