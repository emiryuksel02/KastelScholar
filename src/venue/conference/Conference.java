package venue.conference;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import article.Article;

/**
 * Represents a conference.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class Conference {
    private final Series series;
    private int year;
    private String location;
    private List<Article> articles;
    private Set<String> keywords;

    /**
     * Creates a new conference.
     * 
     * @param series   Series to add the conference.
     * @param year     Conference year.
     * @param location Conference location.
     */
    public Conference(Series series, int year, String location) {
        this.series = series;
        this.year = year;
        this.location = location;
        this.articles = new ArrayList<Article>();
        this.keywords = new LinkedHashSet<String>();
    }

    /**
     * Gets the series of a conference
     * 
     * @return series of a conference
     */
    public Series getSeries() {
        return series;
    }

    /**
     * Gets the year of a conference
     * 
     * @return year of a conference
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the location of a conference
     * 
     * @return location of a conference
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the list of the articles defined in a conference
     * 
     * @return list of articles
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * Adds an article to a conference
     * 
     * @param article Article to add
     */
    public void addArticle(Article article) {
        article.addKeywords(new ArrayList<>(this.keywords));
        this.articles.add(article);
    }

    /**
     * Removes an article at the specified position.
     * 
     * @param index position to remove
     */
    public void removeArticle(int index) {
        this.articles.remove(index);
    }

    /**
     * Adds an article at the specified position.
     * 
     * @param index   position to add
     * @param article article to add
     */
    public void addArticle(int index, Article article) {

        this.articles.add(index, article);
    }

    /**
     * Remove article with the specified id.
     * 
     * @param id Identifier of the article to remove.
     */
    public void removeArticleById(String id) {
        for (Article article : this.articles) {
            if (article.getId().equals(id)) {
                this.articles.remove(article);
                return;
            }
        }
    }

    /**
     * Get keyword set of a conference
     * 
     * @return keyword set of a conference
     */
    public Set<String> getKeywords() {
        return keywords;
    }

    /**
     * Checks if a conference contains an article with a specified identifier.
     * 
     * @param id Identifier of an article
     * @return true if the conference contains an article with a specified
     *         identifier. false otherwise.
     */
    public boolean containsArticle(String id) {
        for (Article article : this.articles) {
            if (article.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds keywords to a conference. Each keyword can only be add once.
     * 
     * @param keywords keywords to add.
     */
    public void addKeywords(List<String> keywords) {

        this.keywords.addAll(keywords);
        for (Article article : this.articles) {
            article.addKeywords(keywords);
        }

    }
}
