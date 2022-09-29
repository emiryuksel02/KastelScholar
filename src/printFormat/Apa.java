package printFormat;

import java.util.ArrayList;
import java.util.List;

import article.Article;
import author.Author;
import venue.conference.Conference;
import venue.journal.JournalInstanceCheck;

/**
 * Represents the print format "APA Simplified".
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */

public class Apa {

    /**
     * Returns a string of author names of an article properly.
     * 
     * Author name is defined as follows: <last name >,â�£<uppercase first letter of
     * first name >.
     * 
     * @param article Article to get authors.
     * 
     * @return String of authors of the article.
     */

    private String getAuthorsWithApa(Article article) {
        List<Author> author = article.getAuthor();
        if (author.size() == 1) {
            char firstLetter = Character.toUpperCase(author.get(0).getName().charAt(0));
            String surname = author.get(0).getSurName();
            return surname + ", " + firstLetter + ".";
        }

        if (author.size() == 2) {
            char firstLetter1 = Character.toUpperCase(author.get(0).getName().charAt(0));
            char firstLetter2 = Character.toUpperCase(author.get(1).getName().charAt(0));

            String surname1 = author.get(0).getSurName();
            String surname2 = author.get(1).getSurName();

            String name1 = surname1 + ", " + firstLetter1 + ".";
            String name2 = surname2 + ", " + firstLetter2 + ".";

            return name1 + ", & " + name2;

        }
        List<String> authorNames = new ArrayList<String>();
        for (Author toAdd : author) {
            char firstLetter = Character.toUpperCase(toAdd.getName().charAt(0));
            authorNames.add(toAdd.getSurName() + ", " + firstLetter + ".");
        }
        String lastAuthor = authorNames.get(authorNames.size() - 1);

        authorNames.remove(authorNames.size() - 1);
        authorNames.add("& " + lastAuthor);

        String authors = String.join(", ", authorNames);
        return authors;
    }

    /**
     * Returns the bibliography of a conference article.
     * 
     * @param article Article to get bibliography.
     * @return Bibliography of the article.
     */

    private String getBibliographyForConferenceArticle(Article article) {
        Conference conference = article.getConference();
        return getAuthorsWithApa(article) + " (" + conference.getYear() + "). " + article.getTitle() + ". "
                + article.getVenue().getName() + ", " + conference.getLocation();
    }

    /**
     * Returns the bibliography of a journal article.
     * 
     * @param article Article to get bibliography.
     * @return Bibliography of the article.
     */

    private String getBibliographyForJournalArticle(Article article) {

        return getAuthorsWithApa(article) + " (" + article.getPublishYear() + "). " + article.getTitle() + ". "
                + article.getVenue().getName();
    }

    /**
     * Returns the bibliography of an article depending on its venue(journal or
     * series).
     * 
     * @param article Article to get bibliography
     * @return Bibliography of the article.
     */

    public String getBibliography(Article article) {
        JournalInstanceCheck journalCheck = new JournalInstanceCheck();
        if (journalCheck.equals(article.getVenue())) {
            return getBibliographyForJournalArticle(article);
        }

        return getBibliographyForConferenceArticle(article);

    }

}
