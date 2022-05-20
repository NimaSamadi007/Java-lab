public class App implements Runnable{
    private static int numOfThreads = 101;
    public static void main(String[] args) throws Exception {
        App threadObj = new App();
        Thread[] threads = new Thread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++){
            threads[i] = new Thread(threadObj);
        } 
        // start all threads:
        for (int i = 0; i < numOfThreads; i++)
            threads[i].start();
        
        try{ // before terminating program wait for all threads
            for (int i = 0; i < numOfThreads; i++)
                threads[i].join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public void run(){
        Counter.inc();
    }
}
