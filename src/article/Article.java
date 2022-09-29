package article;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import author.Author;
import venue.Venue;
import venue.conference.Conference;
import venue.conference.Series;
import venue.conference.SeriesInstanceCheck;

/**
 * Represents an article.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public class Article implements Comparable<Article> {
    private final String id;
    private final String title;
    private final int publishYear;
    private List<Author> author;
    private Venue venue;
    private List<Article> receivedCites;
    private List<Article> givenCites;
    private Set<String> keywords;

    /**
     * Creates a new article.
     * 
     * @param id          Identifier of the article
     * @param title       Title of the article
     * @param publishYear Publishing year
     * @param venue       venue of the article
     */
    public Article(String id, String title, int publishYear, Venue venue) {
        this.venue = venue;
        this.id = id;
        this.title = title;
        this.publishYear = publishYear;
        this.author = new ArrayList<Author>();
        this.receivedCites = new ArrayList<Article>();
        this.givenCites = new ArrayList<Article>();
        this.keywords = new LinkedHashSet<String>();
    }

    /**
     * Gets the identifier of an article.
     * 
     * @return id of an article
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the title of an article
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the publishing year of an article
     * 
     * @return publishing year
     */
    public int getPublishYear() {
        return publishYear;
    }

    /**
     * Gets list of authors of an article
     * 
     * @return authors
     */
    public List<Author> getAuthor() {
        return author;
    }

    /**
     * Gets list of received cites of an article
     * 
     * @return received cites
     */
    public List<Article> getReceivedCites() {
        return receivedCites;
    }

    /**
     * Gets list of given cites of an article.
     * 
     * @return given cites
     */
    public List<Article> getGivenCites() {
        return givenCites;
    }

    /**
     * Gets venue of an article
     * 
     * @return venue
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * Adds author to an article.
     * 
     * @param authors Authors to add
     */
    public void addAuthors(List<Author> authors) {
        this.author.addAll(authors);
    }

    /**
     * Adds a given cite to an article
     * 
     * @param article Article to give cite
     */
    public void addGivenCite(Article article) {
        this.givenCites.add(article);
    }

    /**
     * Adds a received cite to an article
     * 
     * @param article Article from which a citations is received
     */
    public void addReceivedCite(Article article) {
        this.receivedCites.add(article);
    }

    /**
     * Gets set of keywords
     * 
     * @return keywords
     */
    public Set<String> getKeywords() {
        return keywords;
    }

    /**
     * Adds list of keywords to an article.
     * 
     * @param keywords List of keywords to be added to an article.
     */

    public void addKeywords(List<String> keywords) {
        this.keywords.addAll(keywords);
    }

    /**
     * Checks if an article contains the {@link Author author} with the given full
     * name.
     * 
     * @param fullName Author name to be checked.
     * 
     * @return True if an article contains an author with the given name, false
     *         otherwise.
     */

    public boolean checkAutor(String fullName) {

        for (Author author : this.author) {
            if (author.getFullName().equals(fullName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if an article contains all keywords from a list.
     * 
     * @param keywords List of keywords to be checked.
     * 
     * @return True if an article contains all keywords from a list, false
     *         otherwise.
     */

    public boolean containsAllKeywords(List<String> keywords) {
        if (this.keywords.containsAll(keywords)) {
            return true;
        }
        return false;
    }

    /**
     * Gets the {@link Conference conference} of an article.
     * 
     * @return null if the venue of an article is not {@link Series series},
     *         Conference of an article otherwise.
     */

    public Conference getConference() {
        SeriesInstanceCheck check = new SeriesInstanceCheck();
        if (check.equals(this.venue)) {
            for (Conference conference : venue.getConferences()) {
                if (conference.getYear() == this.publishYear) {
                    return conference;
                }
            }
        }

        return null;
    }

    @Override
    public int compareTo(Article other) {
        int authorComparisonResult = 0;
        int sizeComparisonResult = Integer.compare(author.size(), other.author.size());

        /*
         * Compares authors at first. If they're same, it compares the size of the
         * author list with other author.
         */
        for (int i = 0; i < author.size(); i++) {
            if (i > other.author.size() - 1) {
                return sizeComparisonResult;
            }
            int finalResult = author.get(i).compareTo(other.author.get(i));
            if (finalResult != 0) {
                authorComparisonResult = finalResult;
                return authorComparisonResult;

            }
        }

        if (sizeComparisonResult != 0) {
            return sizeComparisonResult;
        }

        /*
         * Compares titles lexicographical.
         */

        int titleComparisonResult = title.compareTo(other.title);
        if (titleComparisonResult != 0) {

            return titleComparisonResult;
        }

        /*
         * Compares publishing years.
         */

        int publishingYearComparisonResult = Integer.compare(publishYear, other.publishYear);

        if (publishingYearComparisonResult != 0) {

            return publishingYearComparisonResult;
        }

        /*
         * Compares identifiers.
         */

        return id.compareTo(other.id);
    }

}
