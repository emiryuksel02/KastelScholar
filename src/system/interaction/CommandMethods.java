package system.interaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;

import system.KastelScholar;
import system.error.ErrorCheck;

/**
 * This class is created to shorten main Command class
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class CommandMethods extends CommandMethodsHelper {
    private static final String JACCARD_EMPTY = "1.000";
    private static final String JACCARD_ONE_SET_EMPTY = "0.000";

    private static final String LIST_SEPARATOR = ";";
    private static final String BLANK = " ";

    private static final String NOT_FOUND = "not found.";

    private static final String AUTHOR = "author";
    private static final String SERIES = "series";
    private static final String ACM = "acm";

    private static final int WHOLE_MATCH = 0;

    private static final int ADD_AUTHOR_PARAMETER_NAME = 1;

    private static final int ADD_JOURNAL_PARAMETER_NAME = 1;
    private static final int ADD_JOURNAL_PARAMETER_PUBLISHER = 2;

    private static final int ADD_SERIES_PARAMETER_NAME = 1;

    private static final int ADD_CONFERENCE_PARAMETER_SERIES = 1;
    private static final int ADD_CONFERENCE_PARAMETER_YEAR = 2;
    private static final int ADD_CONFERENCE_PARAMETER_LOCATION = 3;

    private static final int ADD_ARTICLE_TO_PARAMETER_VENUE = 1;
    private static final int ADD_ARTICLE_TO_VENUE_NAME = 2;
    private static final int ADD_ARTICLE_TO_PARAMETER_ID = 3;
    private static final int ADD_ARTICLE_TO_PARAMETER_YEAR = 4;
    private static final int ADD_ARTICLE_TO_PARAMETER_TITLE = 5;

    private static final int WRITTEN_BY_PARAMETER_ID = 1;
    private static final int WRITTEN_BY_PARAMETER_AUTHORLIST = 2;

    private static final int CITES_PARAMETER_GIVER = 1;
    private static final int CITES_PARAMETER_RECEIVER = 2;

    private static final int ADD_KEYWORDS_TO_VENUE_PARAMETER_TYPE = 0;
    private static final int ADD_KEYWORDS_TO_TYPE = 1;
    private static final int ADD_KEYWORDS_TO_VENUE_PARAMETER_NAME = 1;

    private static final int PUBLICATIONS_BY_PARAMETER_AUTHORS = 1;

    private static final int IN_PROCEEDINGS_PARAMETER_SERIES = 1;
    private static final int IN_PROCEEDINGS_PARAMETER_YEAR = 2;

    private static final int FIND_BY_KEYWORDS_PARAMETER_LIST = 1;

    private static final int JACCARD_WHOLE_LIST = 1;

    private static final int SIMILARITY_PARAMETER_FIRST = 1;
    private static final int SIMILARITY_PARAMETER_SECOND = 2;

    private static final int GINDEX_PARAMETER_AUTHOR = 1;

    private static final int COAUTHORS_OF_PARAMETER_AUTHOR = 1;

    private static final int FOREIGN_CITATIONS_OF_PARAMETER_AUTHOR = 1;

    private static final int PRINT_BIBLIOGRAPHY_STYLE = 1;
    private static final int PRINT_BIBLIOGRAPHY_ID_LIST = 2;

    /**
     * The add author command to add a new author to the system.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void addAuthor(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String author = matcher.group(ADD_AUTHOR_PARAMETER_NAME);
        if (errorCheck.authorExists(author)) {
            throw new InputException(ErrorMessages.AUTHOR_ALREADY_EXISTS.toString());
        }
        kastelScholar.addAuthorToSystem(author);
    }

    /**
     * The add journal command to add a new journal to the system.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void addJournal(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String journal = matcher.group(ADD_JOURNAL_PARAMETER_NAME);
        String publisher = matcher.group(ADD_JOURNAL_PARAMETER_PUBLISHER);

        if (errorCheck.journalExists(journal)) {
            throw new InputException(ErrorMessages.JOURNAL_ALREADY_EXISTS.toString());
        }
        kastelScholar.addJournalToSystem(journal, publisher);
    }

    /**
     * The add series command to add new series to the system.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void addSeries(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String series = matcher.group(ADD_SERIES_PARAMETER_NAME);

        if (errorCheck.seriesExists(series)) {
            throw new InputException(ErrorMessages.SERIES_ALREADY_EXISTS.toString());
        }
        kastelScholar.addSeriesToSystem(series);
    }

    /**
     * The add conference command to add new conference to the system.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void addConference(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String series = matcher.group(ADD_CONFERENCE_PARAMETER_SERIES);
        String year = matcher.group(ADD_CONFERENCE_PARAMETER_YEAR);
        String location = matcher.group(ADD_CONFERENCE_PARAMETER_LOCATION);

        if (!errorCheck.seriesExists(series)) {
            throw new InputException(ErrorMessages.SERIES_NOT_FOUND.toString());
        }
        if (!yearValid(year)) {
            throw new InputException(ErrorMessages.YEAR_NOT_VALID.toString());
        }
        if (errorCheck.conferenceExists(series, year)) {
            throw new InputException(ErrorMessages.CONFERENCE_IN_YEAR_ALREADY_EXIST.toString());
        }
        kastelScholar.addConferenceToSystem(series, year, location);
    }

    /**
     * The add article command to add a new article to the system.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void addArticleTo(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String venue = matcher.group(ADD_ARTICLE_TO_PARAMETER_VENUE);
        String venueName = matcher.group(ADD_ARTICLE_TO_VENUE_NAME);
        String id = matcher.group(ADD_ARTICLE_TO_PARAMETER_ID);
        String year = matcher.group(ADD_ARTICLE_TO_PARAMETER_YEAR);
        String title = matcher.group(ADD_ARTICLE_TO_PARAMETER_TITLE);

        if (!errorCheck.venueExists(venue, venueName)) {
            throw new InputException(ErrorMessages.VENUE_NOT_FOUND.toString());
        }
        if (!yearValid(year)) {
            throw new InputException(ErrorMessages.YEAR_NOT_VALID.toString());
        }
        if (errorCheck.articleExists(id)) {
            throw new InputException(ErrorMessages.ARTICLE_CAN_HAVE_ONLY_ONE_VENUE.toString());
        }
        if (venue.equals(SERIES)) {
            if (!errorCheck.conferenceExists(venueName, year)) {
                throw new InputException(ErrorMessages.CONFERENCE_IN_YEAR_NOT_EXIST.toString());
            }
        }
        kastelScholar.addArticleToSystem(venue, venueName, id, year, title);
    }

    /**
     * The written by command to define new authors to an article.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void writtenBy(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String id = matcher.group(WRITTEN_BY_PARAMETER_ID);
        String authorList = matcher.group(WRITTEN_BY_PARAMETER_AUTHORLIST);
        ArrayList<String> authors = extractList(authorList, LIST_SEPARATOR);

        if (!errorCheck.articleExists(id)) {
            throw new InputException(ErrorMessages.ARTICLE_NOT_FOUND.toString());
        }
        for (String authorToCheck : authors) {
            if (!errorCheck.authorExists(authorToCheck)) {
                throw new InputException(ErrorMessages.AUTHOR_NOT_FOUND.toString());
            }
        }
        if (errorCheck.authorAlreadyAddedtoArticle(id, authors)) {
            throw new InputException(ErrorMessages.AUTHOR_DUPLICATE.toString());
        }
        kastelScholar.writtenBy(id, authors);
    }

    /**
     * The cites command to give citations to an article from an article.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void cites(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String giver = matcher.group(CITES_PARAMETER_GIVER);
        String receiver = matcher.group(CITES_PARAMETER_RECEIVER);

        if (giver.equals(receiver)) {
            throw new InputException(ErrorMessages.CITE_SAME_ARTICLE.toString());
        }
        if (!errorCheck.articleExists(giver) || !errorCheck.articleExists(receiver)) {
            throw new InputException(ErrorMessages.ARTICLE_NOT_FOUND.toString());
        }
        int dateComparisonResult = errorCheck.checkDate(giver, receiver);

        if (dateComparisonResult != 1) {
            throw new InputException(ErrorMessages.CITE_DATE_N0T_VALID.toString());
        }
        kastelScholar.cites(giver, receiver);
    }

    /**
     * The add keywords to command to add new keywords to journal,series or article
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void addKeywordsTo(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        /*
         * The method gets only 2 different strings at first. type : contains command
         * keywords: keywords list as a string.
         */
        String type = matcher.group(ADD_KEYWORDS_TO_TYPE);
        String keywords = matcher.group(WHOLE_MATCH);

        keywords = keywords.substring(keywords.lastIndexOf(":") + 1);
        ArrayList<String> keywordsList = extractList(keywords, LIST_SEPARATOR);

        String venueType = null;
        String name = null;

        if (type.startsWith("series") || type.startsWith("journal")) {
            ArrayList<String> values = extractList(type, " ");
            venueType = values.get(ADD_KEYWORDS_TO_VENUE_PARAMETER_TYPE);
            name = values.get(ADD_KEYWORDS_TO_VENUE_PARAMETER_NAME);
            if (!errorCheck.venueExists(venueType, name)) {
                throw new InputException(ErrorMessages.VENUE_NOT_FOUND.toString());
            }

        } else {
            name = type;
            if (!errorCheck.articleExists(name)) {
                throw new InputException(ErrorMessages.ARTICLE_NOT_FOUND.toString());
            }
        }
        kastelScholar.addKeywordTo(venueType, name, keywordsList);
    }

    /**
     * The all publications command to list all publications in database.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void allPublications(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        List<String> allPublications = kastelScholar.getAllPublicationsFromSystem();
        if (!allPublications.isEmpty()) {
            printAll(allPublications);
        }
    }

    /**
     * The list invalid publications command to list all publications which do not
     * have an author.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void listInvalidPublications(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        List<String> invalidPublications = kastelScholar.listInvalidPublications();
        if (!invalidPublications.isEmpty()) {
            printAll(invalidPublications);
        }
    }

    /**
     * The publications by command to list the identifiers of all publications in
     * which at least one of the given authors participates.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void publicationsBy(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String authors = matcher.group(PUBLICATIONS_BY_PARAMETER_AUTHORS);
        ArrayList<String> authorList = extractList(authors, LIST_SEPARATOR);
        for (String authorName : authorList) {
            if (!errorCheck.authorExists(authorName)) {
                throw new InputException(AUTHOR + " \"" + authorName + "\" " + NOT_FOUND);
            }
        }
        List<String> publications = kastelScholar.getPublicationsBy(authorList);
        if (!publications.isEmpty()) {
            printAll(publications);
        }
    }

    /**
     * The in proceedings command to list the identifiers of all publications
     * published in the specified conference series in the specified year.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void inProceedings(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String series = matcher.group(IN_PROCEEDINGS_PARAMETER_SERIES);
        String year = matcher.group(IN_PROCEEDINGS_PARAMETER_YEAR);
        if (!errorCheck.seriesExists(series)) {
            throw new InputException(SERIES + " \"" + series + "\" " + NOT_FOUND);
        }
        if (!errorCheck.conferenceExists(series, year)) {
            throw new InputException(ErrorMessages.CONFERENCE_IN_YEAR_NOT_EXIST.toString());
        }
        printAll(kastelScholar.getInProceedings(series, year));
    }

    /**
     * The find by keywords command to list the identifiers of all publications that
     * have all of the specified keywords.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void findByKeywords(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        String keywords = matcher.group(FIND_BY_KEYWORDS_PARAMETER_LIST);
        List<String> keywordList = extractList(keywords, LIST_SEPARATOR);

        List<String> publications = kastelScholar.getFindByKeywords(keywordList);

        if (!publications.isEmpty()) {
            printAll(publications);
        }
    }

    /**
     * The jaccard command to calculate the so-called Jaccard index for two sets of
     * keywords.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void jaccard(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        String list = matcher.group(JACCARD_WHOLE_LIST);
        ArrayList<String> listsToCompare = extractList(list, BLANK);
        listsToCompare.removeIf(String::isEmpty);
        int listSize = listsToCompare.size();

        // Checks if a one set is empty.

        if (listSize == 1) {
            System.out.println(JACCARD_ONE_SET_EMPTY);
            return;
        }

        // Checks if both sets are empty.
        if (listSize == 0) {
            System.out.println(JACCARD_EMPTY);
            return;
        }
        ArrayList<String> firstList = extractList(listsToCompare.get(0), LIST_SEPARATOR);
        ArrayList<String> secondList = extractList(listsToCompare.get(1), LIST_SEPARATOR);

        Set<String> firstSet = new HashSet<String>();
        Set<String> secondSet = new HashSet<String>();

        firstSet.addAll(firstList);
        secondSet.addAll(secondList);
        double result = kastelScholar.calculateJaccard(firstSet, secondSet);
        System.out.println(formatResult(result));
    }

    /**
     * The similarity command to calculate how similar two publications are in terms
     * of their keywords.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void similarity(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String first = matcher.group(SIMILARITY_PARAMETER_FIRST);
        String second = matcher.group(SIMILARITY_PARAMETER_SECOND);

        if (!errorCheck.articleExists(first) || !errorCheck.articleExists(second)) {
            throw new InputException(ErrorMessages.ARTICLE_NOT_FOUND.toString());
        }
        double result = kastelScholar.calculateSimilarity(first, second);
        System.out.println(formatResult(result));
    }

    /**
     * The g-index command to calculate g index of an author based on the
     * publications stored in the system.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void gIndex(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String author = matcher.group(GINDEX_PARAMETER_AUTHOR);

        if (!errorCheck.authorExists(author)) {
            throw new InputException(ErrorMessages.AUTHOR_NOT_FOUND.toString());
        }
        int result = kastelScholar.calculateGindex(author);
        System.out.println(result);
    }

    /**
     * The coauthors of command to list all co-authors of an author.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void coAuthorsOf(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String author = matcher.group(COAUTHORS_OF_PARAMETER_AUTHOR);

        if (!errorCheck.authorExists(author)) {
            throw new InputException(ErrorMessages.AUTHOR_NOT_FOUND.toString());
        }
        List<String> coAuthors = kastelScholar.getCoAuthors(author);
        printAll(coAuthors);
    }

    /**
     * The foreign citations of command to list all foreign citations of an author.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void foreignCitationsOf(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String author = matcher.group(FOREIGN_CITATIONS_OF_PARAMETER_AUTHOR);

        if (!errorCheck.authorExists(author)) {
            throw new InputException(ErrorMessages.AUTHOR_NOT_FOUND.toString());
        }
        List<String> foreignCitations = kastelScholar.getForeignCitationsOf(author);
        printAll(foreignCitations);
    }

    /**
     * The print bibliography command to print a bibliography with a specified
     * format.
     * 
     * @param matcher       The regex matcher
     * @param kastelScholar The instance of a system to be manipulated
     */
    public void printBibliography(MatchResult matcher, KastelScholar kastelScholar) throws InputException {
        ErrorCheck errorCheck = new ErrorCheck(kastelScholar);
        String style = matcher.group(PRINT_BIBLIOGRAPHY_STYLE);
        String identifiers = matcher.group(PRINT_BIBLIOGRAPHY_ID_LIST);
        ArrayList<String> idList = extractList(identifiers, LIST_SEPARATOR);

        for (String id : idList) {
            if (!errorCheck.articleExists(id)) {
                throw new InputException(ErrorMessages.ARTICLE_NOT_FOUND.toString());
            }
            if (errorCheck.hasNoAuthor(id)) {
                throw new InputException(ErrorMessages.INVALID_PUBLICATION.toString());
            }
        }
        List<String> toPrint = kastelScholar.getBibliography(style, idList);
        Set<String> removeDuplicates = new LinkedHashSet<>(toPrint);

        List<String> bibliographyFinal = new ArrayList<>(removeDuplicates);
        if (style.equals(ACM)) {
            printAllWithAcm(bibliographyFinal);
            return;
        }
        printAll(bibliographyFinal);
    }
}