package system.interaction;

import java.util.Scanner;

import system.KastelScholar;

/**
 * This program provides a system for literature and citation management.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public final class Main {

    private static final String ERROR_UTILITY_CLASS_INSTANTIATION = "Utility class cannot be instantiated.";
    private static final String ERROR_MESSAGE_PREFIX = "Error, ";

    /**
     * Constructs a new instance of Main.
     *
     * @throws IllegalStateException if constructor is called because Main is a
     *                               utility class.
     */
    private Main() {
        throw new IllegalStateException(ERROR_UTILITY_CLASS_INSTANTIATION);
    }

    /**
     * Main entry point to the app.
     * 
     * @param args The arguments that are passed to the program at launch as array.
     */
    public static void main(String[] args) {
        KastelScholar kastelScholar = new KastelScholar();
        Command command = null;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                command = Command.executeMatching(scanner.nextLine(), kastelScholar);

            } catch (InputException exception) {
                System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
            }
        } while (command == null || command.isRunning());

    }

}
