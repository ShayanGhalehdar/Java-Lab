package lab6;

public class ThreadMaker extends Thread
{
    public static int threadNumber=1;
    
    @Override
    public void run()
    {
        if (threadNumber <= 50)
        {
            this.setName(String.valueOf(threadNumber));
            threadNumber=threadNumber+1;
            Thread newThread = new Thread(new ThreadMaker());
            newThread.start();
            try{
            newThread.join();
            }
            catch (InterruptedException ex) {}
            System.out.println("Hello from thread "+ this.getName());
        }
    }
}