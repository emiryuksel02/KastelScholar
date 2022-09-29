package venue.conference;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import article.Article;
import venue.Venue;

/**
 * Represents a series.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class Series implements Venue {
    private final String name;
    private List<Conference> conferences;
    private Set<String> keywords;

    /**
     * Creates a new series.
     * 
     * @param name Series name.
     */
    public Series(String name) {
        this.name = name;
        this.conferences = new ArrayList<Conference>();
        this.keywords = new LinkedHashSet<String>();

    }

    @Override
    public List<Conference> getConferences() {
        return conferences;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Article> getArticles() {
        List<Article> articles = new ArrayList<Article>();
        for (Conference conference : this.conferences) {
            articles.addAll(conference.getArticles());
        }
        return articles;
    }

    /**
     * Gets a set of keywords of a series
     * 
     * @return set of keywords
     */
    public Set<String> getKeywords() {
        return keywords;
    }

    @Override
    public void updateArticle(Article article) {
        int index = -1;
        Conference conferenceToChange = null;
        for (Conference conference : this.conferences) {
            for (Article articleToUpdate : conference.getArticles()) {
                if (article.getId().equals(articleToUpdate.getId())) {
                    conferenceToChange = conference;
                    index = conferenceToChange.getArticles().indexOf(articleToUpdate);
                    break;
                }
            }
        }

        if (index >= 0) {
            conferenceToChange.removeArticle(index);
            conferenceToChange.addArticle(index, article);
        }
    }

    @Override
    public void addKeywords(List<String> keywords) {
        for (Conference conference : this.conferences) {
            conference.addKeywords(keywords);
        }
        this.keywords.addAll(keywords);

    }

    @Override
    public void addArticle(Article article) {
        article.addKeywords(new ArrayList<>(this.keywords));
        for (Conference conferenceToAdd : conferences) {
            if (article.getPublishYear() == conferenceToAdd.getYear()) {
                conferenceToAdd.addArticle(article);
                return;
            }
        }
    }

    /**
     * Adds conference to a series.
     * 
     * @param conference Conference to add.
     */
    public void addConference(Conference conference) {
        this.conferences.add(conference);
    }

}