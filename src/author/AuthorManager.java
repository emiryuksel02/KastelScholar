package author;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import article.Article;
import article.ArticleManager;

/**
 * Provides methods for author management.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */

public class AuthorManager extends ArticleManager {

    /**
     * Adds an author to the system database.
     * 
     * @param fullName Full name of the author to add.
     */

    protected void addAuthor(String fullName) {

        String[] split = fullName.split(" ");
        String name = split[0];
        String surname = split[1];

        this.addAuthor(new Author(name, surname));

    }

    /**
     * Calculates g-index of an author.
     * 
     * @param author
     * @return g-index of an author.
     */
    protected int gIndex(Author author) {

        /*
         * If the author has written no articles, it returns 0.
         */

        if (author.getArticles().isEmpty()) {
            return 0;
        }

        /*
         * Adds number of received citations of each article to a list.
         */
        List<Integer> cites = new ArrayList<Integer>();
        for (Article article : author.getArticles()) {
            cites.add(article.getReceivedCites().size());
        }

        /*
         * If the articles of the author have no received citations, it returns 0.
         */

        if (containsOnlyZeros(cites)) {
            return 0;
        }

        return gIndexWithList(cites);

    }

    /**
     * Checks if an integer list consists of zeros.
     * 
     * @param list List to check.
     * @return true if the list only has zero values, false otherwise.
     */

    private boolean containsOnlyZeros(List<Integer> list) {
        int check = 0;
        for (int value : list) {
            check = check + value;
        }

        if (check == 0) {
            return true;
        }

        return false;
    }

    /**
     * Algorithm to calculate g-index from a list of integers.
     * 
     * @param articles
     * @return
     */

    private int gIndexWithList(List<Integer> articles) {
        Collections.sort(articles, Collections.reverseOrder());

        int i = 0;
        int sumCites = 0;

        while (i < articles.size()) {

            int k = (i + 1) * (i + 1);

            sumCites = sumCites + articles.get(i);

            if (sumCites >= k) {
                if (sumCites < (i + 2) * (i + 2)) {

                    return i + 1;
                }
            }
            i++;

        }

        return 0;
    }

    /**
     * Return a list of co-authors of an author. Co-authors are authors who are
     * jointly involved in any publication. A co-author can be added maximum one
     * time.
     * 
     * @param author Author to check for co-authors
     * @return co-authors of the author.
     */

    protected List<Author> findCoAuthors(Author author) {
        ArrayList<Author> coAuthors = new ArrayList<Author>();

        for (Article articleToCheck : author.getArticles()) {
            for (Author authorToCheck : articleToCheck.getAuthor()) {
                if (!alreadyAdded(authorToCheck, coAuthors)
                        && !authorToCheck.getFullName().equals(author.getFullName())) {
                    coAuthors.add(authorToCheck);
                }
            }
        }

        return coAuthors;
    }

    /**
     * Checks if an author is already added to a list of authors.
     * 
     * @param author  Author to check.
     * @param authors Authors list to check.
     * @return true if the author is already added, false otherwise.
     */

    private boolean alreadyAdded(Author author, List<Author> authors) {

        for (Author authorToAdd : authors) {
            if (authorToAdd.getFullName().equals(author.getFullName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if an article contains a co-author of an author.
     * 
     * @param article Article to check.
     * @param author  Author to check.
     * @return true if the article contains a co-author, false otherwise.
     */
    private boolean containsCoAuthor(Article article, Author author) {
        List<Author> coAuthors = findCoAuthors(author);

        for (Author authorToCheck : coAuthors) {

            if (article.checkAutor(authorToCheck.getFullName())) {
                return true;
            }

        }

        return false;
    }

    /**
     * Gives a list of foreign citations of an author. A foreign citation is defined
     * as a citation that does not come from an article which is written by a
     * co-author or the author himself.
     * 
     * @param author Author to give foreign citations.
     * @return Foreign citations of an author.
     */

    protected List<Article> giveForeignCitations(Author author) {
        List<Article> foreignCitations = new ArrayList<Article>();
        List<Article> allCitations = author.getAllCitations();

        for (Article article : allCitations) {
            if (!containsCoAuthor(article, author)) {
                foreignCitations.add(article);
            }
        }

        return foreignCitations;
    }

}
