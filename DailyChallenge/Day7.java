package dailycodingproblem;

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
        
        System.out.println(countWays(length));

    }
}
