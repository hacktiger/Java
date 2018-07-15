/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailycodingproblem;

/**
 * Given a list of numbers and a number k, return whether any two numbers from
 * the list add up to k.
 *
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is
 * 17.
 *
 * Bonus: Can you do this in one pass?
 *
 * @author Dell
 */
public class Day1 {
    /**
     * 
     * @param array
     * @param sum
     * @return 
     */
    public static boolean solveProblemOnePass(int[] array, int sum){
        return false;
    }
    
    /**
     * 
     * @param array
     * @param sum
     * @return 
     */
    public static boolean solveProblemManyPass(int[] array, int sum){
        for(int i = 0; i < array.length - 2; i++){
            for(int k = 0; k < array.length; k++){
                if( array[i] + array[k] == sum && i != k ){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * @param array
     */
    public static void printArray(int[] array){
        System.out.print("[ ");
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.print("]");
        System.out.println("\n");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long rt = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        int sum;
        boolean result;
        int[] numArray = {10, 15, 3, 7};
        // Prints out the array
        printArray(numArray);
        // Get the sum you wanted
        sum = 25;
        //Solve the problem
        result = solveProblemManyPass(numArray, sum);
        if(result)
            System.out.println(" Yes ");
        else
            System.out.println(" No ");
        // Prints runtime/ Memory
        System.out.println("Memory : " + rt);
    }
    

}
