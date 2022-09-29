package venue.journal;

/**
 * Class to check if an object is an instance of journal.
 * 
 * @author Emir Yuksel
 * @version 1.0
 * 
 * 
 */
public class JournalInstanceCheck {
    @Override
    public boolean equals(Object o) {
        return o instanceof Journal;

    }
}
