public class App {
    static private int totalNumOfThreads = 50;
    public static void main(String[] args) throws Exception {
        startThread(1);        
    }
    public static void startThread(int threadIdx){
        MyThread thObj = new MyThread(threadIdx);
        Thread th = new Thread(thObj);
        if (threadIdx < totalNumOfThreads) // This isn't last thread, continue ...
            startThread(threadIdx+1);
        th.start();
        try{ // wait for it to be finished
            th.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
