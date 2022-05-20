public class MyThread implements Runnable{
    private int threadIdx;
    public MyThread(int threadIdxIn){
        threadIdx = threadIdxIn;
    }
    public void run(){
        System.out.println("Hello from Thread " + threadIdx);        
    }
}
