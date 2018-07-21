package dailycodingproblem;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the
 * number of ways it can be decoded.
 *
 * For example, the message '111' would give 3, since it could be decoded as
 * 'aaa', 'ka', and 'ak'.
 *
 * You can assume that the messages are decodable. For example, '001' is not
 * allowed.
 *
 * @author Dell
 */
class Day7 {

    /**
     * @effects generate the coding map
     * @return
     */
    public static Map generateMap() {
        Map<Integer, Character> map = new HashMap<>();
        int count = 1;
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(count, c);
            count++;
        }
        return map;
    }

    public static void printMap(Map<Integer, Character> map) {
        map.entrySet().forEach((m) -> {
            System.out.println(m.getKey() + " " + m.getValue());
        });
    }

    /**
     *
     * @param message
     * @return
     */
    public static String decodeMessage(int message) {
        String result = null;

        return result;
    }
    // A simple recursive program to find n'th fibonacci number
    static int fib(int n)
    {
       if (n <= 1)
          return n;
       return fib(n-1) + fib(n-2);
    }
     
    // Returns number of ways to reach s'th stair
    static int countWays(int s)
    {
        return fib(s + 1);
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int message = 1121;
        int length = String.valueOf(message).length();
        Map<Integer, Character> map = new HashMap<>();
        map = generateMap();
        
        System.out.println(countWays(length));

    }
}
