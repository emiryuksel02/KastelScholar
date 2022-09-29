package system.interaction;

/**
 * Provides error messages for an interactive session.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public enum ErrorMessages {
    /**
     * Pops up when the user tries to add an article multiple times.
     */
    ARTICLE_CAN_HAVE_ONLY_ONE_VENUE("an article can be published in exactly one venue."),
    /**
     * Pops up when a conference is already defined in the specified year.
     */
    CONFERENCE_IN_YEAR_ALREADY_EXIST("a conference is already added to this series in this year."),
    /**
     * Pops up when the publication is invalid.
     */
    INVALID_PUBLICATION("you entered an invalid publication"),
    /**
     * Pops up when the command is not valid.
     */
    INVALID_COMMAND("not a valid command"),
    /**
     * Pops up when user tries to add an author with a same name.
     */
    AUTHOR_ALREADY_EXISTS("author with same name already added."),
    /**
     * Pops up when user tries to add a same journal.
     */
    JOURNAL_ALREADY_EXISTS("journal is already added"),
    /**
     * Pops up when user tries to add a same series.
     */
    SERIES_ALREADY_EXISTS("serie is already added"),
    /**
     * Pops up when conference series cannot be found.
     */
    SERIES_NOT_FOUND("conference series not found."),
    /**
     * Pops up when the year is not valid.
     */
    YEAR_NOT_VALID("year is not valid"),
    /**
     * Pops up when a venue cannot be found.
     */
    VENUE_NOT_FOUND("venue not found"),
    /**
     * Pops up when an author cannot be found.
     */
    AUTHOR_NOT_FOUND("author not found"),
    /**
     * Pops up when user adds an author multiple times to an article.
     */
    AUTHOR_DUPLICATE("you can add an author maximum one time to an article"),
    /**
     * Pops up when an article cannot be found.
     */
    ARTICLE_NOT_FOUND("article not found"),
    /**
     * Pops up when an article gives cite itself.
     */
    CITE_SAME_ARTICLE("publications cannot cite themselves"),
    /**
     * Pops up when an article cites an article published after it.
     */
    CITE_DATE_N0T_VALID("an article can only cite articles published before it."),
    /**
     * Pops up when a conference in a specified year does not exist.
     */
    CONFERENCE_IN_YEAR_NOT_EXIST("conference in this year does not exist.");

    /**
     * Error message
     */
    private final String message;

    /**
     * Creates a new ErrorMesages.
     * 
     * @param message message
     */
    ErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
