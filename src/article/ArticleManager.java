package article;

import java.util.List;

import author.Author;
import venue.Venue;
import venue.journal.JournalManager;

/**
 * Provides methods for article management.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */

public class ArticleManager extends JournalManager {

    /**
     * Adds all given authors in a list to an article with the given identifier.
     * Then it updates the corresponding list (journals or series) in the system
     * database.
     * 
     * @param id      Identifier of the {@link Article article}
     * @param authors List of authors
     */

    protected void addAuthortoArticle(String id, List<Author> authors) {
        Article article = getArticleById(id);

        article.addAuthors(authors);
        for (Author authorToAdd : authors) {
            authorToAdd.addArticle(article);
        }
        Venue venue = article.getVenue();
        updateLists(venue, article);

    }

    /**
     * Gives a cite from an article to an article. Then it updates the corresponding
     * list (journals or series) in the system database.
     * 
     * @param giverID    Article which gives a cite.
     * @param receiverID Article which receives a cite.
     */

    protected void giveCite(String giverID, String receiverID) {

        Article giver = getArticleById(giverID);
        Article receiver = getArticleById(receiverID);
        giver.addGivenCite(receiver);
        receiver.addReceivedCite(giver);

        Venue venue1 = giver.getVenue();
        Venue venue2 = receiver.getVenue();

        updateLists(venue1, giver);
        updateLists(venue2, receiver);

    }

}
