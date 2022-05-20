import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        // int[] arr = {1, 7, 4, 3, 8, 6, 2, 5};
        Random rd = new Random();
        int[] arr = new int[256];
        for (int i = 0; i < 256; i++)
            arr[i] = rd.nextInt(1000);

        System.out.println("Random input array:");
        printArr(arr);    
        System.out.print("Is sorted? ");
        System.out.println(isSorted(arr));

        Sort sorter = new Sort(arr, 0, 255);
        Thread thSorter = new Thread(sorter);
        thSorter.start();

        try{
            thSorter.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Sorted array:");
        printArr(arr);
        System.out.print("Is sorted? ");
        System.out.println(isSorted(arr));

    }

    // checks if an array is sorted
    static boolean isSorted(int[] arr){
        for(int i = 0; i < arr.length-1; i++)
            if (arr[i+1] < arr[i])
                return false;
        return true;
    }

    static void printArr(int[] arr){
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + ", ");
        System.out.print("]\n");        
    }
}
