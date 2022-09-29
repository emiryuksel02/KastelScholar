package venue.journal;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import article.Article;
import venue.Venue;
import venue.conference.Conference;

/**
 * Represents a journal.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class Journal implements Venue {
    private String name;
    private String publisher;
    private List<Article> articles;
    private Set<String> keywords;

    /**
     * Creates a new journal.
     * 
     * @param name      Name of the journal.
     * @param publisher Publisher name of the journal.
     */
    public Journal(String name, String publisher) {
        this.name = name;
        this.publisher = publisher;
        this.articles = new ArrayList<Article>();
        this.keywords = new LinkedHashSet<String>();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the publisher name of a journal.
     * 
     * @return publisher name of a journal.
     */
    public String getPublisher() {
        return publisher;
    }

    @Override
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * Gets all the keywords in a journal
     * 
     * @return the keywords
     */
    public Set<String> getKeywords() {
        return keywords;
    }

    @Override
    public void updateArticle(Article article) {
        int place = -1;

        for (Article articleToUpdate : this.articles) {
            if (article.getId().equals(articleToUpdate.getId())) {
                place = this.articles.indexOf(articleToUpdate);
                break;
            }
        }
        if (place >= 0) {
            this.articles.remove(place);
            this.articles.add(place, article);
        }
    }

    @Override
    public void addKeywords(List<String> keywords) {

        this.keywords.addAll(keywords);
        for (Article article : this.articles) {
            article.addKeywords(keywords);
        }

    }

    @Override
    public void addArticle(Article article) {
        this.articles.add(article);

    }

    @Override
    public List<Conference> getConferences() {

        return null;
    }

}
