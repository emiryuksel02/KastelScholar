package venue.journal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import article.Article;
import venue.conference.ConferenceManager;

/**
 * Contains methods for journal management.
 * 
 * @author Emir Yuksel
 * @version 1.0
 * 
 *
 */
public class JournalManager extends ConferenceManager {

    /**
     * Adds a journal to the system database.
     * 
     * @param name      Name of the journal to add.
     * @param publisher Name of the journal publisher to add.
     */
    protected void addJournal(String name, String publisher) {

        addJournal(new Journal(name, publisher));

    }

    /**
     * Adds an article to a journal.
     * 
     * @param name  Journal name to get an article.
     * @param id    Identifier of an article to add.
     * @param year  Publishing year of an article to add.
     * @param title Title of an article to add.
     */
    protected void addArticleToJournal(String name, String id, int year, String title) {
        List<Journal> journals = getJournals();

        Iterator<Journal> iterator = journals.iterator();
        while (iterator.hasNext()) {
            Journal currentJournal = iterator.next();
            if (currentJournal.getName().equals(name)) {

                Article article = new Article(id, title, year, currentJournal);
                article.addKeywords(new ArrayList<>(currentJournal.getKeywords()));
                currentJournal.addArticle(article);
                updateLists(currentJournal, article);
                return;
            }
        }

    }

}