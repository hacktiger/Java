package dailycodingproblem;

import java.util.HashMap;
import java.util.Map;

/**
 * Finished
 * Given an integer k and a string s, find the length of the longest substring
 * that contains at most k distinct characters.
 *
 * For example, given s = "abcba" and k = 2, the longest substring with k
 * distinct characters is "bcb".
 *
 * @author Dell
 */
class Day13 {

    /**
     *
     */
    public static String solve(int k, String s) {
        //Variables
        int start;
        int end;
        int count;
        //
        for (int i = 0; i < s.length(); i++) {
            count = 1;
            start = i;
            end = i + 1;

            for (int j = i + 1; j < s.length() - 1; j++) {
                end++;
                //System.out.println(s.charAt(j)  + " and c = " + c);
                if (s.charAt(j) == s.charAt(i)) {
                    //System.out.println("true");
                    count++;
                    //System.out.println(count);
                    //if count no if met
                    if (count == k) {
                        // check so that is MUST be a substring
                        if (!s.substring(start, end).equals(s)) 
                            return s.substring(start, end);
                    }
                }
            }
            //System.out.println("Loop no :" + i + " start/end :  " + start + "/" + end);
        }

        return null;
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        int k = 2;
        String s = "abcba";
        String ans = solve(k, s);
        System.out.println("ANSWER : " + ans);
    }
}
