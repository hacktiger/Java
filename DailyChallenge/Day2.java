/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailycodingproblem;

/**
 * Given an array of integers, return a new array such that each element at
 * index i of the new array is the product of all the numbers in the original
 * array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be
 * [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would
 * be [2, 3, 6].
 *
 * Follow-up: what if you can't use division? division ????
 *
 * @author Dell
 */
public class Day2 {
    /**
     * @effects : solve the problem with 2 loops
     * @param array
     * @return int[] ( result )
     */
    public static int[] solveProblem(int[] array) {
        int[] result = new int[array.length];
        for(int i = 0; i < array.length; i++){
            int temp = 1;
            for(int j = 0; j < array.length; j++){
                if(i != j)
                    temp *= array[j];
            }
            result[i] = temp;
        }
        return result;
    }

    /**
     * @effects : prints out the given array in human readable form
     * @param array
     */
    public static void printArray(int[] array) {
        System.out.print("[ ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("]");
        System.out.println("\n");

    }

    /**
     * init the arrays, prints result in console
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //the given array
        int[] numArr = {1, 2, 3, 4, 5};
            //prints out the given array
        printArray(numArr);
        // the result array
        int[] multiArr = solveProblem(numArr);
            //prints the result
        printArray(multiArr);
        
    }

}
