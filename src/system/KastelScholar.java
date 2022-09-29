package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import article.Article;
import author.Author;
import author.AuthorManager;
import printFormat.Acm;
import printFormat.Apa;
import venue.conference.Series;

/**
 * Brings methods to manage the system together and makes them more readable.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */

public class KastelScholar extends AuthorManager {

    private static final String SERIES = "series";
    private static final String JOURNAL = "journal";

    private static final String PRINT_FORMAT_ACM = "acm";
    private static final String PRINT_FORMAT_APA = "apa";

    /**
     * Runs the addAuthor() from "AuthorManager".
     * 
     * @param author Author to add.
     */

    public void addAuthorToSystem(String author) {
        this.addAuthor(author);
    }

    /**
     * Runs addJournal() from "JournalManager".
     * 
     * @param journal   Journal name to add.
     * @param publisher Publisher name to add.
     */

    public void addJournalToSystem(String journal, String publisher) {
        this.addJournal(journal, publisher);
    }

    /**
     * Runs addSeries() from "ConferenceManager"
     * 
     * @param series Series name to add.
     */

    public void addSeriesToSystem(String series) {
        this.addSeries(series);
    }

    /**
     * Runs addConference() from "ConferenceManager".
     * 
     * @param series   Series name to add.
     * @param year     Year of a conference.
     * @param location Location of a conference.
     */

    public void addConferenceToSystem(String series, String year, String location) {

        int conferenceYear = Integer.valueOf(year);
        this.addConference(series, conferenceYear, location);
    }

    /**
     * Runs addArticleToConference() from "ConferenceManager" if the venue is
     * "series".
     * 
     * Runs addArticleToJournal() from "JournalManager" if the venue is "journal".
     * 
     * @param venue Venue type of the article.
     * @param name  Name of the venue.
     * @param id    Identifier of the article to add.
     * @param year  Publishing year of the article.
     * @param title Title of the article.
     */

    public void addArticleToSystem(String venue, String name, String id, String year, String title) {
        int articleYear = Integer.valueOf(year);
        if (venue.equals(SERIES)) {

            this.addArticleToConference(name, id, articleYear, title);
        }

        if (venue.equals(JOURNAL)) {
            this.addArticleToJournal(name, id, articleYear, title);
        }
    }

    /**
     * Runs addAuthorToArticle() from "ArticleManager".
     * 
     * @param id      Identifier of the article.
     * @param authors Author list to add to an article.
     */

    public void writtenBy(String id, List<String> authors) {
        List<Author> authorsInSystem = this.getAuthors();
        List<Author> authorsToAdd = new ArrayList<Author>();

        for (String fullName : authors) {

            for (Author author : authorsInSystem) {
                if (author.getFullName().equals(fullName)) {
                    authorsToAdd.add(author);
                }
            }

        }

        this.addAuthortoArticle(id, authorsToAdd);

    }

    /**
     * Runs giveCite() from "ArticleManager".
     * 
     * @param giver    Article identifier which gives a cite.
     * @param receiver Article identifier which receives a cite.
     */

    public void cites(String giver, String receiver) {
        this.giveCite(giver, receiver);
    }

    /**
     * Runs addKeywordsToJournal() from "SystemDatabase" if the venue is "journal".
     * 
     * Runs addKeywordsToSeries() from "SystemDatabase" if the venue is "series".
     * 
     * Runs addKeywordsToArticle() from "SystemDatabase" otherwise.
     * 
     * @param venue    Venue to add keywords.
     * @param name     Name of the parameter which receives keywords.
     * @param keywords Keyword list to add.
     */

    public void addKeywordTo(String venue, String name, List<String> keywords) {
        if (venue != null) {
            if (venue.equals(JOURNAL)) {
                this.addKeywordsToJournal(name, keywords);
                return;
            }
            if (venue.equals(SERIES)) {
                this.addKeywordsToSeries(name, keywords);
                return;
            }
        }

        this.addKeywordsToArticle(name, keywords);
    }

    /**
     * Returns identifiers of all articles in the system, respectively.
     * 
     * @return A sorted list of identifiers.
     */

    public List<String> getAllPublicationsFromSystem() {
        List<Article> articles = this.getAllArticles();
        List<String> articleId = new ArrayList<String>();

        for (Article article : articles) {
            articleId.add(article.getId());
        }

        return sortById(articleId);
    }

    /**
     * Runs invalidPublications() from "SystemDatabase".
     * 
     * @return A sorted list of identifiers of invalid publications.
     */

    public List<String> listInvalidPublications() {
        return sortById(this.invalidPublications());
    }

    /**
     * Runs "publicationsBy() from "SystemDatabase".
     * 
     * @param authors Author list get publications.
     * @return A sorted list of identifiers of publications.
     */

    public List<String> getPublicationsBy(List<String> authors) {

        return sortById(this.publicationsBy(authors));
    }

    /**
     * Runs inProceedings() from "SystemDatabase".
     * 
     * @param seriesName Name of the series to check.
     * @param year       Publishing of the article to check.
     * @return Sorted list of identifiers.
     */

    public List<String> getInProceedings(String seriesName, String year) {

        Series series = this.searchSeries(seriesName);

        return sortById(this.inProceedings(series, Integer.valueOf(year)));
    }

    /**
     * Runs findByKeywords() from "SystemDatabase".
     * 
     * @param keywords keywords to check.
     * @return A sorted list of identifiers of publications.
     */
    public List<String> getFindByKeywords(List<String> keywords) {

        return sortById(this.findByKeywords(keywords));
    }

    /**
     * Runs jaccard() from "SystemDatabase".
     * 
     * @param first  Set off elements to calculate.
     * @param second Set of elements to calculate.
     * @return Jaccard index.
     */

    public float calculateJaccard(Set<String> first, Set<String> second) {
        return this.jaccard(first, second);
    }

    /**
     * Runs similarity() from "SystemDatabase".
     * 
     * @param firstId  Article identifier to calculate.
     * @param secondId Article identifier to calculate.
     * @return How similar two publications are in terms of their keywords.
     */

    public float calculateSimilarity(String firstId, String secondId) {
        return this.similarity(firstId, secondId);
    }

    /**
     * Runs gIndex() from "AuthorManager".
     * 
     * @param author Author name to calculate.
     * @return g-index of an author.
     */

    public int calculateGindex(String author) {
        Author authorToCalculate = this.searchAuthor(author);

        return this.gIndex(authorToCalculate);
    }

    /**
     * Runs findCoAuthors() from "AuthorManager".
     * 
     * @param author Author name to get co-authors.
     * @return List of the co-authors.
     */

    public List<String> getCoAuthors(String author) {
        Author authorToSearch = this.searchAuthor(author);

        List<Author> coAuthors = this.findCoAuthors(authorToSearch);
        List<String> coAuthorNames = new ArrayList<String>();

        for (Author a : coAuthors) {
            coAuthorNames.add(a.getFullName());
        }

        return coAuthorNames;
    }

    /**
     * Runs giveForeignCitations() from "AuthorManager".
     * 
     * @param author Author name to get foreign citations.
     * @return Sorted list of identifiers of foreign citations.
     */

    public List<String> getForeignCitationsOf(String author) {
        List<Article> foreignCitations = this.giveForeignCitations(this.searchAuthor(author));
        List<String> foreignCitationNames = new ArrayList<String>();

        for (Article article : foreignCitations) {
            if (!article.getAuthor().isEmpty()) {
                foreignCitationNames.add(article.getId());
            }
        }

        return sortById(foreignCitationNames);
    }

    /**
     * Runs the getBibliography() method depending on print format.
     * 
     * @param format Format to print(Acm or Apa).
     * @param id     Identifier of articles to print.
     * @return Bibliography
     */

    public List<String> getBibliography(String format, List<String> id) {
        List<Article> queuedArticles = new ArrayList<Article>();

        List<Article> articles = new ArrayList<Article>();

        List<String> formattedArticles = new ArrayList<String>();

        for (String idToSearch : id) {
            articles.add(this.getArticleById(idToSearch));
        }

        /*
         * Sorts the articles.
         */

        PriorityQueue<Article> queue = new PriorityQueue<Article>();
        Iterator<Article> queueAdd = articles.iterator();
        while (queueAdd.hasNext()) {
            queue.add(queueAdd.next());
        }

        Iterator<Article> iteratorQ = queue.iterator();
        while (iteratorQ.hasNext()) {
            queuedArticles.add(queue.poll());

        }

        /*
         * Checks print format and runs the corresponding method.
         */
        for (Article article : queuedArticles) {
            if (format.equals(PRINT_FORMAT_ACM)) {
                Acm acm = new Acm();
                formattedArticles.add(acm.getBibliography(article));
            }

            if (format.equals(PRINT_FORMAT_APA)) {
                Apa apa = new Apa();
                formattedArticles.add(apa.getBibliography(article));
            }
        }

        return formattedArticles;
    }

    /**
     * Sorts articles.
     * 
     * @param identifiers
     * @return
     */
    private List<String> sortById(List<String> identifiers) {
        if (!identifiers.isEmpty()) {
            List<String> sortedList = new ArrayList<String>();
            PriorityQueue<String> queue = new PriorityQueue<>();
            queue.addAll(identifiers);

            while (queue.size() != 0) {
                sortedList.add(queue.poll());
            }

            return sortedList;
        }
        return Collections.emptyList();
    }

}
