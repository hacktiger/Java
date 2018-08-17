/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailycodingproblem;

/**
 * The edit distance between two strings refers to the minimum number of
 * character insertions, deletions, and substitutions required to change one
 * string to the other. For example, the edit distance between “kitten” and
 * “sitting” is three: substitute the “k” for “s”, substitute the “e” for “i”,
 * and append a “g”.
 *
 * Given two strings, compute the edit distance between them.
 *
 * @author Dell
 */
class Day31 {

    /**
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int computeDistance(String s1, String s2) {
        int count = 0;
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 > len2) {
            for (int i = 0; i < len2; i++) {
                if (s2.charAt(i) != s1.charAt(i)) {
                    count++;
                }
            }
            count += len1 - len2;
        } else if (len1 < len2) {
            for (int i = 0; i < len1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    count++;
                }
            }
            count += len2 - len1;
        } else {
            for (int i = 0; i < len1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        String str1 = "kitten";
        String str2 = "sitting";
        
        int ans = computeDistance(str1, str2);
        System.out.println("Ans : " + ans);

    }
}
