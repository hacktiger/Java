package dailycodingproblem;


/**
 * 
 * @author Dell
 */
public class BubbleSort {
    public static void printArray(int arr[]){
        for(int i = 0 ; i < arr.length ; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
    }
    /**
     * 
     * @param array
     * @return 
     */
    public static int[] bubbleSort(int[] array){
        boolean done = false;
        int passNo = 0;
        while(!done){
            done = true;
            for(int i = 0 ; i < array.length - 1 ; i++){
                if(array[i] < array[i+1]){
                    done = false;
                    int temp;
                    temp = array[i+1];
                    array[i+1] = array[i];
                    array[i] = temp;
                }
            }
            passNo++;
            System.out.print("Pass nnumber " + passNo + " : ");
            printArray(array);
        }

        return null;
    }
    /**
     * MAIN METHOD
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] numArr = {25,-21,63,75,35,0,6,1,24,5,6};
        bubbleSort(numArr);      
    }
}
