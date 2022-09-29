package venue.conference;

import java.util.ArrayList;
import java.util.List;

import article.Article;
import system.SystemDatabase;

/**
 * Provides methods for conference management.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class ConferenceManager extends SystemDatabase {

    /**
     * Adds a new series to the database.
     * 
     * @param name Series name to add.
     */
    protected void addSeries(String name) {
        addSeries(new Series(name));

    }

    /**
     * Adds a new conference to a series.
     * 
     * @param series   Series name to add the conference.
     * @param year     Conference year.
     * @param location Conference location.
     */
    protected void addConference(String series, int year, String location) {

        Series seriesToAdd = this.searchSeries(series);

        addConference(seriesToAdd, new Conference(seriesToAdd, year, location));

    }

    /**
     * Adds article to a series/conference.
     * 
     * @param name  Series name of the conference.
     * @param id    Article identifier.
     * @param year  Publishing year of the article.
     * @param title Title of the article
     */
    protected void addArticleToConference(String name, String id, int year, String title) {

        List<Series> series = getSeries();
        for (Series seriesToCheck : series) {
            if (seriesToCheck.getName().equals(name)) {
                Article article = new Article(id, title, year, seriesToCheck);
                List<String> keywords = new ArrayList<>(seriesToCheck.getKeywords());
                article.addKeywords(keywords);
                seriesToCheck.addArticle(article);
                updateLists(seriesToCheck, article);

            }
        }

    }

}
