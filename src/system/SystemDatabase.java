package system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import article.Article;
import author.Author;
import venue.Venue;
import venue.conference.Conference;
import venue.conference.Series;
import venue.conference.SeriesInstanceCheck;
import venue.journal.Journal;
import venue.journal.JournalInstanceCheck;

/**
 * This class is for management and data storage for KastelScholar system.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */

public class SystemDatabase {
    private List<Author> authors;
    private List<Journal> journals;
    private List<Series> series;

    /**
     * Creates a new system database.
     */

    protected SystemDatabase() {
        this.authors = new ArrayList<Author>();
        this.journals = new ArrayList<Journal>();
        this.series = new ArrayList<Series>();
    }

    /**
     * Gets authors in database
     * 
     * @return authors in database
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Gets journals in database.
     * 
     * @return journals in database.
     */
    public List<Journal> getJournals() {
        return journals;
    }

    /**
     * Gets series in a database.
     * 
     * @return series in a database.
     */
    public List<Series> getSeries() {
        return series;
    }

    /**
     * Gets all articles in the database.
     * 
     * @return All articles in the database.
     */

    public List<Article> getAllArticles() {
        List<Venue> venues = new ArrayList<Venue>();
        ArrayList<Article> articles = new ArrayList<Article>();

        venues.addAll(journals);
        venues.addAll(series);

        for (Venue venue : venues) {
            articles.addAll(venue.getArticles());
        }

        return articles;

    }

    /**
     * Adds keywords to a journal.
     * 
     * @param name     Name of the journal to add keywords.
     * @param keywords List of keywords to add.
     */
    protected void addKeywordsToJournal(String name, List<String> keywords) {
        for (Journal journal : this.journals) {
            if (journal.getName().equals(name)) {
                journal.addKeywords(keywords);
                return;
            }
        }
    }

    /**
     * Adds keywords to a series.
     * 
     * @param name     Name of series to add keywords.
     * @param keywords List of keywords to add.
     */
    protected void addKeywordsToSeries(String name, List<String> keywords) {

        for (Series series : this.series) {
            if (series.getName().equals(name)) {
                series.addKeywords(keywords);
                return;
            }
        }

    }

    /**
     * Adds keywords to an article.
     * 
     * @param id       Identifier of the article to add keywords.
     * @param keywords List of keywords to add.
     */
    protected void addKeywordsToArticle(String id, List<String> keywords) {
        List<Article> articles = getAllArticles();

        for (Article article : articles) {
            if (article.getId().equals(id)) {

                article.addKeywords(keywords);
                updateLists(article.getVenue(), article);

            }
        }

    }

    /**
     * This method can be called if a change happened in an article.
     * 
     * @param venue   Venue of the article.
     * @param article Article which is changed.
     */
    protected void updateLists(Venue venue, Article article) {
        JournalInstanceCheck journalCheck = new JournalInstanceCheck();
        SeriesInstanceCheck seriesCheck = new SeriesInstanceCheck();
        if (seriesCheck.equals(venue)) {
            for (Series series : this.series) {
                if (series.getName().equals(venue.getName())) {
                    series.updateArticle(article);
                }
            }
            return;
        }

        if (journalCheck.equals(venue)) {
            for (Journal journal : this.journals) {
                if (journal.getName().equals(venue.getName())) {
                    journal.updateArticle(article);
                }
            }
            return;
        }

    }

    /**
     * Gets the list of invalid publications. Invalid publications are publications
     * without authors.
     * 
     * @return list of invalid publications.
     */
    protected List<String> invalidPublications() {

        List<String> invalidPublications = new ArrayList<String>();
        List<Article> allPublications = getAllArticles();

        for (Article article : allPublications) {
            if (article.getAuthor().isEmpty()) {
                invalidPublications.add(article.getId());
            }
        }

        return invalidPublications;
    }

    /**
     * Gets a list of publication identifiers which are written by each author in a
     * list.
     * 
     * @param searchedAuthors Author names to get publications.
     * @return list of publication identifiers
     */
    protected List<String> publicationsBy(List<String> searchedAuthors) {
        List<Article> allArticles = getAllArticles();
        List<String> publications = new ArrayList<String>();

        for (String author : searchedAuthors) {

            for (Article article : allArticles) {

                if (article.checkAutor(author)) {
                    if (!publications.contains(article.getId())) {
                        publications.add(article.getId());
                    }

                }

            }

        }

        return publications;
    }

    /**
     * Gets the serie depending on its name.
     * 
     * @param seriesName Name of series to search.
     * @return searched serie.
     */
    protected Series searchSeries(String seriesName) {
        for (Series serie : this.series) {
            if (serie.getName().equals(seriesName)) {
                return serie;
            }
        }
        return null;
    }

    /**
     * Returns the identifiers of all publications published in the specified
     * conference series in the specified year.
     * 
     * @param series Series to search.
     * @param year   Year to search.
     * @return list of identifiers.
     */

    protected List<String> inProceedings(Series series, int year) {
        List<String> publications = new ArrayList<String>();
        Series searchedSeries = searchSeries(series.getName());
        List<Article> articles = searchedSeries.getArticles();

        for (Article article : articles) {
            if (article.getPublishYear() == year) {
                publications.add(article.getId());
            }

        }

        return publications;
    }

    /**
     * Returns the identifiers of all publications that have all of the specified
     * keywords.
     * 
     * @param keywords keyword list to check.
     * @return list of identifiers.
     */
    protected List<String> findByKeywords(List<String> keywords) {
        List<String> publications = new ArrayList<String>();
        for (Article article : getAllArticles()) {
            if (article.containsAllKeywords(keywords)) {
                publications.add(article.getId());
            }
        }
        return publications;
    }

    /**
     * Adds a conference to a serie.
     * 
     * @param seriesToGet     Serie who gets a conference.
     * @param conferenceToAdd Conference who will be added.
     */
    protected void addConference(Series seriesToGet, Conference conferenceToAdd) {
        for (Series series : this.series) {
            if (series.getName().equals(seriesToGet.getName())) {
                series.addConference(conferenceToAdd);
                return;
            }
        }
    }

    /**
     * Calculates jaccard-index of 2 lists.
     * 
     * @param keyword1 Set of keywords to calculate.
     * @param keyword2 Set of keywords to calculate.
     * @return jaccard-index
     */
    protected float jaccard(Set<String> keyword1, Set<String> keyword2) {
        Set<String> intersection = new HashSet<String>();
        Set<String> combination = new HashSet<String>();

        for (String toCheck : keyword1) {
            if (keyword2.contains(toCheck)) {
                intersection.add(toCheck);
            }

        }

        for (String toCheck : keyword2) {
            if (keyword1.contains(toCheck)) {
                intersection.add(toCheck);
            }
        }
        combination.addAll(keyword1);
        combination.addAll(keyword2);

        return (float) intersection.size() / combination.size();
    }

    /**
     * Gets an article depending on its identifier.
     * 
     * @param id Identifier of the article.
     * @return Article which is searched by identifier.
     */
    public Article getArticleById(String id) {
        List<Article> articles = getAllArticles();

        Iterator<Article> iterator = articles.iterator();

        while (iterator.hasNext()) {
            Article currentArticle = iterator.next();
            if (currentArticle.getId().equals(id)) {
                return currentArticle;
            }
        }
        return null;
    }

    /**
     * Gives how similar two publications are in terms of their keywords.
     * 
     * @param id1 Identifier of the first article.
     * @param id2 Identifier of the second article.
     * @return Similarity of the articles.
     */
    protected float similarity(String id1, String id2) {

        return jaccard(getArticleById(id1).getKeywords(), getArticleById(id2).getKeywords());
    }

    /**
     * Gives the author with the searched name. If no author found, returns null.
     * 
     * @param fullName Name to search.
     * @return Author who is searched.
     */
    public Author searchAuthor(String fullName) {

        for (Author authorToSearch : this.authors) {
            if (authorToSearch.getFullName().equals(fullName)) {
                return authorToSearch;
            }
        }
        return null;
    }

    /**
     * Adds a journal to the database.
     * 
     * @param journal Journal to add.
     */
    protected void addJournal(Journal journal) {
        this.journals.add(journal);
    }

    /**
     * Adds an author to the database.
     * 
     * @param author Author to add.
     */
    protected void addAuthor(Author author) {
        this.authors.add(author);
    }

    /**
     * Adds a series to the database.
     * 
     * @param series Series to add.
     */
    protected void addSeries(Series series) {
        this.series.add(series);

    }

}