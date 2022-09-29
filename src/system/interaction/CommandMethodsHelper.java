package system.interaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains helper methods for command methods.
 * 
 * @author Emir Yuksel
 * @version 1.0
 *
 */
public class CommandMethodsHelper {
    private static final int MIN_YEAR = 0;
    private static final int MAX_YEAR = 9999;

    private static final int DECIMAL_SECURE = 1000;
    private static final double DECIMAL_SECURE_FORMAT_DOUBLE = 1000.0;

    /**
     * Checks if the year meets the requirements.
     * 
     * @param year Year to check
     * @return true if it meets the requirements, false otherwise.
     */
    protected boolean yearValid(String year) {
        int yearToCheck = Integer.valueOf(year);
        if (yearToCheck < MIN_YEAR || yearToCheck > MAX_YEAR) {
            return false;
        }
        return true;
    }

    /**
     * Returns a list from a string.
     * 
     * @param toExtract String to separate
     * @param separator Common separator
     * @return an ArrayList
     */
    protected ArrayList<String> extractList(String toExtract, String separator) {
        return new ArrayList<String>(Arrays.asList(toExtract.split(separator)));
    }

    /**
     * Prints all strings of a list.
     * 
     * @param list List to print values.
     */
    protected void printAll(List<String> list) {
        for (String toPrint : list) {
            System.out.println(toPrint);
        }
    }

    /**
     * Prints a list with Acm format. It adds bibliography identifier to each value.
     * 
     * @param list List to print values.
     */
    protected void printAllWithAcm(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            int k = i + 1;
            System.out.println("[" + k + "] " + list.get(i));
        }
    }

    /**
     * Change a double value to meet the print requirements.
     * 
     * @param result Result of a calculation.
     * @return A formatted string
     */
    protected String formatResult(double result) {
        double secureDecimals = Math.floor(result * DECIMAL_SECURE) / DECIMAL_SECURE_FORMAT_DOUBLE;
        String toPrint = String.format("%.3f", secureDecimals);
        return toPrint;
    }
}
