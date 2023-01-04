package lab6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    
    private static final int crunchifyThreads = 101;
    
    public static void main(String[] args) throws InterruptedException
    {
        // Q1
        //ThreadMaker newThread = new ThreadMaker();
        //newThread.start();
        
        // Q2
        ExecutorService executor = Executors.newFixedThreadPool(crunchifyThreads);
        for(int i=0;i<crunchifyThreads;i++)
        {
            myThread t = new myThread();            
            executor.execute(t);
        }
        executor.shutdown();
        
    }
}