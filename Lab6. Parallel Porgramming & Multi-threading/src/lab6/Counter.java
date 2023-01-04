package lab6;

public class Counter
{
    static int count = 0;
    
    static void inc()
    {
        if(count <=100)
        {
            try{
            Thread.sleep(10);
            }
            catch(InterruptedException e)
            { e.printStackTrace();}
            
            count++;
        }
        
        if(count > 100)
        {
            System.out.println("Failed");
        }
    }
}
