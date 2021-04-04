package exception;

/**
 * Exception afin de vérifier qu'une partie se déroule bien dans le bon ordre
 */
public class WrongStateException extends Exception {

    public WrongStateException(String message)
    {
        super(message);
    }

}
