package dailycodingproblem;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import java.awt.Graphics;
import javax.swing.*;

/**
 * The area of a circle is defined as πr^2. Estimate π to 3 decimal places using
 * a Monte Carlo method.
 *
 * Hint: The basic equation of a circle is x2 + y2 = r2. S circle = r^2*pi S
 * square = 4(r^2) 4 * P(raindrop inside circle) = pi
 *
 * @author Dell
 */
public class Day14 extends JPanel {
    /**
     * @NOTE:
     *      since the class extends JPanel so I just count it inside the draw
     *      function (which I hope to fix)
     *      The result is displayed in the console
     */
    // Variables for rectangle
    private static final int START_X = 50;
    private static final int START_Y = START_X;
    private static final int RECT_WIDTH = 200;
    private static final int RECT_HEIGHT = RECT_WIDTH;
    //CENTER POINT OF CIRCLE
    private static final int CENTER_X = 150;
    private static final int CENTER_Y = 150;
    private static final int CIRCLE_RADIUS = 100;
    // COUNT
    private int RAINDROP = 0;

    public void setRAINDROP(int RAINDROP) {
        this.RAINDROP = RAINDROP;
    }

    /**
     *
     * @param range
     * @return
     */
    public int randInt(int range) {
        int num = (int) (Math.random() * range + START_X);
        return num;
    }

    /**
     * Draw method
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //variables
        double k = 0;
        int count = 0;
        int numDrop = 100000;
        // draw the rectangle here
        g.drawRect(START_X, START_Y, RECT_WIDTH, RECT_HEIGHT);
        g.drawOval(START_X, START_Y, 200, 200);

        for (int i = 0; i < numDrop; i++) {
            int x_axis = randInt(200);
            int y_axis = randInt(200);
            int cordX = (int) Math.pow(x_axis - 150, 2);
            int cordY = (int) Math.pow(y_axis - 150, 2);
            if (cordX + cordY < Math.pow(CIRCLE_RADIUS, 2)) {
                count++;
                g.setColor(RED);
            } else {
                g.setColor(BLUE);
            }

            g.drawOval(x_axis, y_axis, 1, 1);
        }
        k = (((double) count) / numDrop) * 4;
        System.out.println("Estimattion of PI : " + k);
    }
    // create the GUI explicitly on the Swing event thread

    private static void createAndShowGui() {
        Day14 mainPanel = new Day14();

        JFrame frame = new JFrame("Monte Carlo Method");
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        createAndShowGui();
    }
}
