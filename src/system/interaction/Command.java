package system.interaction;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import system.KastelScholar;

/**
 * This class contains commands for user interaction.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public enum Command {

    /**
     * The add author command to add a new author to the system.
     */
    ADD_AUTHOR("add author (" + Command.REGEX_AUTHOR + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.addAuthor(matcher, kastelScholar);
        }
    },
    /**
     * The add journal command to add a new journal to the system.
     */
    ADD_JOURNAL("add journal (" + Command.REGEX_VENUE_NAME + "),(" + Command.REGEX_PUBLISHER + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.addJournal(matcher, kastelScholar);
        }
    },
    /**
     * The add series command to add new series to the system.
     */
    ADD_SERIES("add series (" + Command.REGEX_VENUE_NAME + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.addSeries(matcher, kastelScholar);
        }
    },
    /**
     * The add conference command to add new conference to the system.
     */
    ADD_CONFERENCE("add conference (" + Command.REGEX_VENUE_NAME + "),(" + Command.REGEX_YEAR + "),("
            + Command.REGEX_LOCATION + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.addConference(matcher, kastelScholar);
        }

    },
    /**
     * The add article command to add a new article to the system.
     */
    ADD_ARTICLE_TO("add article to (" + Command.REGEX_VENUE + ") (" + Command.REGEX_VENUE_NAME + "):("
            + Command.REGEX_ID + "),(" + Command.REGEX_YEAR + "),(" + Command.REGEX_TITLE + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.addArticleTo(matcher, kastelScholar);
        }
    },
    /**
     * The written by command to define new authors to an article.
     */
    WRITTEN_BY("written by (" + Command.REGEX_ID + "),(" + Command.REGEX_AUTHOR_LIST + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.writtenBy(matcher, kastelScholar);
        }
    },
    /**
     * The cites command to give citations to an article from an article.
     */
    CITES("cites (" + Command.REGEX_ID + "),(" + Command.REGEX_ID + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.cites(matcher, kastelScholar);
        }

    },
    /**
     * The add keywords to command to add new keywords to journal,series or article
     */
    ADD_KEYWORDS_TO("add keywords to (" + Command.REGEX_VENUE_OR_ARTICLE + "):" + Command.REGEX_KEYWORDS_LIST) {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.addKeywordsTo(matcher, kastelScholar);
        }
    },
    /**
     * The all publications command to list all publications in database.
     */
    ALL_PUBLICATIONS("all publications") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.allPublications(matcher, kastelScholar);

        }
    },
    /**
     * The list invalid publications command to list all publications who do not
     * have an author.
     */
    LIST_INVALID_PUBLICATIONS("list invalid publications") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.listInvalidPublications(matcher, kastelScholar);
        }
    },
    /**
     * The publications by command to list the identifiers of all publications in
     * which at least one of the given authors participates.
     */
    PUBLICATIONS_BY("publications by (" + Command.REGEX_AUTHOR_LIST + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.publicationsBy(matcher, kastelScholar);
        }
    },
    /**
     * The in proceedings command to list the identifiers of all publications
     * published in the specified conference series in the specified year.
     */
    IN_PROCEEDINGS("in proceedings (" + Command.REGEX_VENUE_NAME + "),(" + Command.REGEX_YEAR + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.inProceedings(matcher, kastelScholar);
        }
    },
    /**
     * The find by keywords command to list the identifiers of all publications that
     * have all of the specified keywords.
     */
    FIND_BY_KEYWORDS("find by keywords (" + Command.REGEX_KEYWORDS_LIST + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.findByKeywords(matcher, kastelScholar);
        }
    },
    /**
     * The jaccard command to calculate the so-called Jaccard index for two sets of
     * keywords.
     */
    JACCARD("jaccard (" + Command.REGEX_JACCARD_LIST + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.jaccard(matcher, kastelScholar);
        }

    },
    /**
     * The similarity command to calculate how similar two publications are in terms
     * of their keywords.
     */
    SIMILARITY("similarity (" + Command.REGEX_ID + "),(" + Command.REGEX_ID + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.similarity(matcher, kastelScholar);
        }
    },
    /**
     * The g-index command to calculate g index of an author based on the
     * publications stored in the system.
     */
    G_INDEX("g-index (" + Command.REGEX_AUTHOR + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.gIndex(matcher, kastelScholar);
        }
    },
    /**
     * The coauthors of command to list all co-authors of an author.
     */
    COAUTHORS_OF("coauthors of (" + Command.REGEX_AUTHOR + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.coAuthorsOf(matcher, kastelScholar);
        }
    },
    /**
     * The foreign citations of command to list all foreign citations of an author.
     */
    FOREIGN_CITATIONS("foreign citations of (" + Command.REGEX_AUTHOR + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.foreignCitationsOf(matcher, kastelScholar);
        }
    },
    /**
     * The print bibliography command to print a bibliography with a specified
     * format.
     */
    PRINT_BIBLIOGRAPHY("print bibliography (" + Command.REGEX_STYLE + "):(" + Command.REGEX_ID_LIST + ")") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            CommandMethods method = new CommandMethods();
            method.printBibliography(matcher, kastelScholar);
        }
    },
    /**
     * The quit command to exit the program.
     */
    QUIT("quit") {

        @Override
        public void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
            this.quit();
        }
    };

    private static final String REGEX_AUTHOR = "[^;\\n,\\s]+\\s[^;\\n,\\s]+";
    private static final String REGEX_AUTHOR_LIST = "([^;\\n,\\s]+\\s[^;\\n,\\s]+)(;([^;\\n,\\s]+\\s[^;\\n,\\s]+))*";
    private static final String REGEX_VENUE_NAME = "[^;\\n,]+";
    private static final String REGEX_LOCATION = "[^;\\n,]+";
    private static final String REGEX_TITLE = "[^;\\n,]+";
    private static final String REGEX_ID = "[a-zäöüß0-9]+";
    private static final String REGEX_ID_LIST = "([a-zäöüß0-9]+)(;[a-zäöüß0-9]+)*";
    private static final String REGEX_PUBLISHER = "[^;\\n,]+";
    private static final String REGEX_VENUE = "series|journal";
    private static final String REGEX_VENUE_OR_ARTICLE = "(series|journal)+\\s+([^;\\n,]+)|[a-zäöüß0-9]+";
    private static final String REGEX_KEYWORDS_LIST = "([a-zäöüß]+)(;[a-zäöüß]+)*";
    private static final String REGEX_STYLE = "acm|apa";
    private static final String REGEX_YEAR = "\\d{1,4}";
    private static final String REGEX_JACCARD_LIST = "((([a-zäöüß]+)(;[a-zäöüß]+)*)|\\s)"
            + "\\s((([a-zäöüß]+)(;[a-zäöüß]+)*)|\\s)";

    private boolean isRunning;
    private final Pattern pattern;

    /**
     * Constructs a new command instance.
     *
     * @param pattern The regex pattern to use for command validation and
     *                processing.
     */
    Command(String pattern) {
        this.isRunning = true;
        this.pattern = Pattern.compile(pattern);

    }

    /**
     * Executes a command.
     * 
     * @param matcher       The regex matcher that contains the groups of user input
     *                      for the command.
     * @param kastelScholar The instance of a system to be manipulated by executing
     *                      a command.
     * @throws InputException if the command contains syntactical or semantic
     *                        errors.
     */
    public abstract void execute(MatchResult matcher, KastelScholar kastelScholar) throws InputException;

    /**
     * Checks an input against all available commands and calls the command if one
     * is found.
     *
     * @param input         The user input.
     * @param kastelScholar The instance of a system to be manipulated by executing
     *                      a command.
     * @return The command that got executed.
     * @throws InputException if no matching command is found. Contains an error
     *                        message.
     */
    public static Command executeMatching(String input, KastelScholar kastelScholar) throws InputException {
        for (Command command : Command.values()) {
            Matcher matcher = command.pattern.matcher(input);
            if (matcher.matches()) {
                command.execute(matcher, kastelScholar);
                return command;
            }
        }

        throw new InputException(ErrorMessages.INVALID_COMMAND.toString());
    }

    /**
     * To check if the program still is closed.
     * 
     * @return true if the program is still running, false otherwise.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Exits the program.
     */
    protected void quit() {
        this.isRunning = false;
    }

}
