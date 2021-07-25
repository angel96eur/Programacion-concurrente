package practica4.ejercicio1;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloDespierta1a1 extends Thread
{

    int cuantos=0;
    Semantica s;
    
    public HiloDespierta1a1(Semantica s,int cuantos)
    {
        this.s=s;
        this.cuantos=cuantos;
        start();
    }
    
    @Override
    public void run()
    {
        for(int i=0;i<cuantos;i++)
        {
            try
            {
                s.despiertaUno();
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(HiloDespierta1a1.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
}
