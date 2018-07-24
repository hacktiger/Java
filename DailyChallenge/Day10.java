package dailycodingproblem;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The best i can do with java right now
 *
 * Implement a job scheduler which takes in a function f and an integer n, and
 * calls f after n milliseconds.
 *
 *
 * @author Dell
 */
public class Day10 {

    /**
     *
     */
    public interface I {

        int sum(int num1, int num2);

    }

    /**
     *
     */
    public static I FUNC = (num1, num2) -> {
        return (num1 + num2);
    };

    /**
     *
     * @param timeDelay
     * @param func
     */
    public static void scheduler(int timeDelay, I func) {
        long delay = timeDelay;
        Timer timer = new Timer("Timer");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // need to pass the function to here
                int test_sum = func.sum(15, 20);
                System.out.println("The sum of 15 and 20 is : " + test_sum);
                System.out.println(delay);
                System.exit(0);
            }
        };

        timer.schedule(task, delay);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // keep this function call here 
        int time = 5000;
        // Call function after 5s
        scheduler(time, FUNC);
    }

}
