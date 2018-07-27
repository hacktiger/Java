package dailycodingproblem;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ez
 * Implement an autocomplete system. That is, given a query string s and a set
 * of all possible query strings, return all strings in the set that have s as a
 * prefix.
 *
 * For example, given the query string de and the set of strings [dog, deer,
 * deal], return [deer, deal].
 *
 * Hint: Try preprocessing the dictionary into a more efficient data structure
 * to speed up queries.
 *
 * @author Dell
 */
class Day11 {

    /**
     *
     * @param input
     * @return
     */
    public static String lookUp(String input, ArrayList<String> arr) {
        String search = input;
        ArrayList<String> result = new ArrayList<>();
        for (String x : arr) {
            if (x.matches(".*" + search + ".*")) {
                result.add(x);
            }
        }
        if(result.isEmpty())
            return "No result found!";
        return result.toString();
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        ArrayList<String> dict = new ArrayList<>();

        // Model dictionary
        dict.add("dog");
        dict.add("dear");
        dict.add("deal");
        dict.add("cat");

        //Ask user input
                boolean done = false;
        while (!done) {
            Scanner sc = new Scanner(System.in);
            System.out.println(" Enter search ( Quit to quit ) : ");
            String userInput = sc.next();
            if( userInput.equalsIgnoreCase("Quit"))
                done = true;      
            String result = lookUp(userInput,dict);
            System.out.println(result);
        }

    }
}
