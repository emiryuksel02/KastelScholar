package system.interaction;

/**
 * An exception indicating that a user input is invalid.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class InputException extends Exception {

    private static final long serialVersionUID = -5708866488023557364L;

    /**
     * Constructs a new instance of InputException.
     *
     * @param message The error message to display to the user.
     */
    public InputException(String message) {
        super(message);
    }

}
