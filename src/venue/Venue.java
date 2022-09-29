package venue;

import java.util.List;

import article.Article;
import venue.conference.Conference;

/**
 * A venue is either a journal or a series.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public interface Venue {
    /**
     * Gets a list of all articles defined in a venue.
     * 
     * @return List of articles
     */
    List<Article> getArticles();

    /**
     * Adds an article to a venue.
     * 
     * @param article Article to add.
     */
    void addArticle(Article article);

    /**
     * Updates an article in a venue if it's attributes are changed.
     * 
     * @param article Article to update
     */
    void updateArticle(Article article);

    /**
     * Gets the name of a venue.
     * 
     * @return name of the venue.
     */
    String getName();

    /**
     * Adds keywords to a venue.
     * 
     * @param keywords Keyword list to add.
     */
    void addKeywords(List<String> keywords);

    /**
     * Gets all conferences defined in a venue(returns null in a journal, since
     * journals cannot have conferences.)
     * 
     * @return conferences
     */
    List<Conference> getConferences();

}
