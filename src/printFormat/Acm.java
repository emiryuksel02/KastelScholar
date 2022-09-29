package printFormat;

import java.util.ArrayList;
import java.util.List;

import article.Article;
import author.Author;
import venue.conference.Conference;
import venue.journal.JournalInstanceCheck;

/**
 * Represents the print format "ACM Simplified".
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class Acm {

    /**
     * Returns a string of author names of an article properly.
     * 
     * Author name is defined as follows: <first name >â�£<last name >.
     * 
     * @param article Article to get authors.
     * 
     * @return String of authors of the article.
     */

    private String getAuthorsWithAcm(Article article) {
        List<Author> authors = article.getAuthor();

        if (authors.size() == 1) {
            return authors.get(0).getFullName();
        }

        if (authors.size() == 2) {
            String firstAuthorName = authors.get(0).getFullName();
            String secondAuthorName = authors.get(1).getFullName();

            return firstAuthorName + " and " + secondAuthorName;

        }

        List<String> names = new ArrayList<String>();

        for (Author author : authors) {
            names.add(author.getFullName());
        }

        String lastAuthorName = names.get(names.size() - 1);
        names.remove(names.size() - 1);
        names.add("and " + lastAuthorName);

        return String.join(", ", names);
    }

    /**
     * Returns the bibliography of a conference article.
     * 
     * @param article Article to get bibliography.
     * @return Bibliography of the article.
     */
    private String getBibliographyForConferenceArticle(Article article) {
        Conference conference = article.getConference();

        String authors = getAuthorsWithAcm(article);
        String title = article.getTitle();
        final String proceedings = "In Proceedings of";
        String seriesName = article.getVenue().getName();
        int conferenceYear = conference.getYear();
        String location = conference.getLocation();

        return authors + ". " + title + ". " + proceedings + " " + seriesName + ", " + conferenceYear + ", " + location
                + ".";

    }

    /**
     * Returns the bibliography of a journal article.
     * 
     * @param article Article to get bibliography.
     * @return Bibliography of the article.
     */

    private String getBibliographyForJournalArticle(Article article) {
        String authors = getAuthorsWithAcm(article);
        int year = article.getPublishYear();
        String articleTitle = article.getTitle();
        String journalTitle = article.getVenue().getName();

        return authors + ". " + year + ". " + articleTitle + ". " + journalTitle + ".";

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
