package author;

import java.util.ArrayList;
import java.util.List;

import article.Article;

/**
 * Represents an author.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */

public class Author implements Comparable<Author> {
    private final String name;
    private final String surName;
    private final String fullName;
    private List<Article> articles;

    /**
     * Creates a new author.
     * 
     * @param name    Name of the author.
     * @param surName Surname of the author.
     */

    public Author(String name, String surName) {
        this.name = name;
        this.surName = surName;
        this.fullName = name + " " + surName;
        this.articles = new ArrayList<Article>();

    }

    /**
     * Gets the name of an author.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the surname of an author.
     * 
     * @return surname
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Gets the full name of an author.
     * 
     * @return full name
     */

    public String getFullName() {
        return fullName;
    }

    /**
     * Gets the list of articles which are written by an author.
     * 
     * @return the articles which are written by an author.
     */

    public List<Article> getArticles() {
        return articles;
    }

    /**
     * Adds the {@link Article article} to an author.
     * 
     * @param article to add.
     */

    public void addArticle(Article article) {
        this.articles.add(article);
    }

    /**
     * Gets all citations of an author by iterating through articles written by the
     * author.
     * 
     * @return Articles who cites an article of an author.
     */

    public List<Article> getAllCitations() {
        List<Article> citations = new ArrayList<Article>();

        for (Article article : this.articles) {
            citations.addAll(article.getReceivedCites());
        }

        return citations;
    }

    @Override
    public int compareTo(Author other) {

        /*
         * Compares author surname and name lexicographical.
         */

        int compareResult = surName.compareTo(other.surName);
        if (compareResult == 0) {
            return name.compareTo(other.name);
        }

        return compareResult;
    }
}
