package system.error;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import article.Article;
import author.Author;
import system.KastelScholar;
import venue.conference.Conference;
import venue.conference.Series;
import venue.journal.Journal;

/**
 * Provides methods to check errors in an user interaction.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class ErrorCheck {
    /**
     * System instance to check errors.
     */
    private KastelScholar kastelScholar;

    /**
     * Creates a new ErrorCheck.
     * 
     * @param systemdatabase
     */
    public ErrorCheck(KastelScholar systemdatabase) {
        this.kastelScholar = systemdatabase;
    }

    /**
     * Checks if a conference in a year exists in a series.
     * 
     * @param series Series name to check.
     * @param year   Year to check.
     * @return True if a conference exists, False otherwise.
     */
    public boolean conferenceExists(String series, String year) {
        List<Series> seriesInDatabase = kastelScholar.getSeries();
        for (Series toCheck : seriesInDatabase) {
            if (toCheck.getName().equals(series)) {

                List<Conference> conferences = toCheck.getConferences();
                for (Conference conference : conferences) {
                    if (conference.getYear() == Integer.valueOf(year)) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    /**
     * Checks if an author with the given name exists in the database.
     * 
     * @param fullName Name to check.
     * @return True if an author exist, False otherwise.
     */
    public boolean authorExists(String fullName) {
        Iterator<Author> iterator = kastelScholar.getAuthors().iterator();

        while (iterator.hasNext()) {
            Author currentAuthor = iterator.next();
            if (currentAuthor.getFullName().equals(fullName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a venue already exists in the database depending on its type and
     * name.
     * 
     * @param venue venue type to check.
     * @param name  venue name to check.
     * @return true if it exists, false otherwise
     */
    public boolean venueExists(String venue, String name) {
        if (venue.equals("series")) {
            return seriesExists(name);
        }
        return journalExists(name);
    }

    /**
     * Checks if an article exists in the database.
     * 
     * @param id Identifier of an article to check.
     * @return True if an article with the searched identifiers exists. False
     *         otherwise.
     */
    public boolean articleExists(String id) {
        List<Article> articles = kastelScholar.getAllArticles();

        Iterator<Article> iterator = articles.iterator();

        while (iterator.hasNext()) {
            Article currentArticle = iterator.next();
            if (currentArticle.getId().equals(id)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Checks if an author from a list of authors is already defined in an article.
     * 
     * @param articleID Article identifier to check.
     * @param authors   Author list to check.
     * @return True if the author from the list is already added to the article.
     *         False otherwise.
     */
    public boolean authorAlreadyAddedtoArticle(String articleID, ArrayList<String> authors) {
        /*
         * Checks if the list contains duplicates.
         */
        Set<String> nameSet = new HashSet<String>(authors);
        if (nameSet.size() < authors.size()) {
            return true;
        }

        ArrayList<String> names = new ArrayList<String>();

        ArrayList<Author> allAuthors = new ArrayList<Author>();

        for (String authorName : authors) {
            allAuthors.add(kastelScholar.searchAuthor(authorName));
        }

        /*
         * Adds all names to the names list.
         * 
         */
        Article articleToCheck = kastelScholar.getArticleById(articleID);

        for (int i = 0; i < articleToCheck.getAuthor().size(); i++) {
            names.add(articleToCheck.getAuthor().get(i).getFullName());
        }

        for (int i = 0; i < allAuthors.size(); i++) {
            if (names.contains(allAuthors.get(i).getFullName())) {
                return true;
            }

        }

        return false;
    }

    /**
     * Checks if a journal with the searched name exists in the database.
     * 
     * @param name Name of journal to search.
     * @return True if it exists, false otherwise.
     */
    public boolean journalExists(String name) {
        Iterator<Journal> iterator = kastelScholar.getJournals().iterator();
        while (iterator.hasNext()) {
            Journal currentJournal = iterator.next();
            if (currentJournal.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a series with the searched name exists in the database.
     * 
     * @param name Name of series to search.
     * @return True if it exists, false otherwise.
     */
    public boolean seriesExists(String name) {
        for (Series series : kastelScholar.getSeries()) {
            if (series.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Compares the publishing years of two articles.
     * 
     * @param first  Article identifier to compare.
     * @param second Article identifier to compare.
     * @return 0 if their publishing year are same. 1 if the first article is
     *         published after second article. -1 if the first article is published
     *         before second article.
     */
    public int checkDate(String first, String second) {
        Article giver = kastelScholar.getArticleById(first);
        Article receiver = kastelScholar.getArticleById(second);

        if (first.equals(second)) {
            return 0;
        }
        if (giver.getPublishYear() <= receiver.getPublishYear()) {
            return -1;
        }
        return 1;
    }

    /**
     * Checks if an article has no author.
     * 
     * @param articleID Identifier of the article to check.
     * @return true if it has no authors. false otherwise.
     */
    public boolean hasNoAuthor(String articleID) {
        if (kastelScholar.getArticleById(articleID).getAuthor().isEmpty()) {
            return true;
        }

        return false;
    }

}
