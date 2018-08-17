/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailycodingproblem;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Compute the running median of a sequence of numbers. That is, given a stream
 * of numbers, print out the median of the list so far on each new element.
 *
 * Recall that the median of an even-numbered list is the average of the two
 * middle numbers.
 *
 * For example, given the sequence [2, 1, 5, 7, 2, 0, 5], your algorithm should
 * print out:
 *
 * 2
 * 1.5 2 3.5 2 2 2
 *
 * @author Dell
 */
class Day31 {

    /**
     *
     * @param args
     */
    public static void main(String args[]) {

    	// Hold the numbers
    	ArrayList<Integer> list = new ArrayList<>();

        /////// Creating a stream of new numbers in the list
    	Thread thread = new Thread(new Runnable() {
    		@Override
    		public void run() {
    			boolean done = false;
                ///// Stream size
    			int count = 0;
    			while (!done) {
                	/// randomize input
    				int temp = (int) ((Math.random() * 5) + 1);
    				count++;
    				if (count == 20) {
    					done = true;
    					break;
    				}
                    ///Add new num
    				list.add(temp);

    				printList(list);
    				computeMedian(list);

    				try {
    					/// do this every 1s
    					Thread.sleep(1000);
    				} catch (InterruptedException ex) {
    					Logger.getLogger(Day31.class.getName()).log(Level.SEVERE, null, ex);
    				}
    			}

    		}


            /**
            * @effects : prints out the list
            */
            private void printList(ArrayList<Integer> list) {
            	for (Integer x : list) {
            		System.out.print(x + ", ");
            	}
            }

            private void computeMedian(ArrayList<Integer> list) {
            	if (list.size() % 2 != 0) {
                    ///if number of indeces is odd
            		double result = list.get((list.size() - 1) / 2);
            		//print median
            		System.out.println("Median : " + result);
            	} else {
            		//if even get 2 middle numbers
            		double temp1 = list.get(list.size() / 2);
            		double temp2 = list.get(list.size() / 2 - 1);
            		//print median
            		System.out.println("Median : " + (temp1 + temp2) / 2);
            	}
            }
        });
    	thread.start();

    }
}
