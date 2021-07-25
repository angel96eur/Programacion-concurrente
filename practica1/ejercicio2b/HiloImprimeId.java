package practica1.ejercicio2b;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloImprimeId implements Runnable
{

    int n=0;
    int id=0;
    Random time=new Random();

    HiloImprimeId(int n,int id)
    {
        this.n=n;
        this.id=id;
    }

    @Override
    public void run()
    {

        for(int i=0;i<100;i++)
        {
            Thread h=Thread.currentThread();
            for(n=1;n<50;n++)
            {
                h.setName("HiloPrincipal " + id + ": ");
                try
                {
                    Thread.sleep(time.nextInt(1001));
                }
                catch(InterruptedException ex)
                {
                    Logger.getLogger(HiloImprimeId.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.printf(h.getName() + n + "\n");
                n++;
            }
        }

    }

}
