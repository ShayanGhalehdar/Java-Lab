package lab6;

import static lab6.Counter.inc;

public class myThread extends Thread
{    
    @Override
    public void run()
    {
        inc();
    }
}