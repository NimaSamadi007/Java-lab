public class Sort implements Runnable{
    private int[] array;
    private int startIdx;
    private int endIdx;
    private int len;
    public Sort(int[] inArr, int startIdx, int endIdx){
        array = inArr;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        len = endIdx-startIdx+1;
    }

    public void run(){
        if (this.len == 2){ // now you can sort the array
            if (array[startIdx] > array[endIdx]) // swap two locations
                swap();
            return;
        }
        else{ // split the array and create threads
            Sort sorterFirstHalf = new Sort(array, startIdx, startIdx + len/2-1); // one thread for sorting first half
            Sort sorterSecondHalf = new Sort(array, startIdx + len/2, endIdx); // another thread for sorting second half
            Thread th1 = new Thread(sorterFirstHalf);
            Thread th2 = new Thread(sorterSecondHalf);
            th1.start();
            th2.start();
            // wait for threads to be finished
            try{
                th1.join();
                th2.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        // now threads have sorted half arrays. It's time to merge them!
        mergeTwoHalfArrays(startIdx, startIdx + len/2-1, startIdx + len/2, endIdx);
        return;
    }

    private void swap(){
        int tmp = array[endIdx];
        array[endIdx] = array[startIdx];
        array[startIdx] = tmp;
    }

    // merges two half arrays,
    // first array: [sIdx1, ..., eIdx1] => start index 1 to end index 1
    // second array: [sIdx2, ..., eIdx2] => start index 2 to end index 2
    private void mergeTwoHalfArrays(int sIdx1, int eIdx1, int sIdx2, int eIdx2){
        int ptr1 = sIdx1;
        int ptr2 = sIdx2;

        int[] tmpArr = new int[len];
        
        // compare values
        for (int oIdx = 0; oIdx < len; oIdx++){
            if (ptr2 > eIdx2){ // copy from first half since second half is empty
                tmpArr[oIdx] = array[ptr1];
                ptr1++;
            }
            else if (ptr1 > eIdx1){ // copy from seond half since second half is empty
                tmpArr[oIdx] = array[ptr2];
                ptr2++;    
            }
            else if (array[ptr1] < array[ptr2]){  
                tmpArr[oIdx] = array[ptr1];
                ptr1++;
            }
            else if(array[ptr1] >= array[ptr2]){
                tmpArr[oIdx] = array[ptr2];
                ptr2++;    
            }
        }
        // copy tmp to array:
        for (int i = 0; i < len; i++)
            array[sIdx1+i] = tmpArr[i];
    }
}
