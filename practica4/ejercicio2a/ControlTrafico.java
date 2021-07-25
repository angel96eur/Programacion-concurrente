package practica4.ejercicio2a;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlTrafico
{
    
    int rojoCruzando=0;
    int azulCruzando=0;
    
    public synchronized void entraRojo()
    {
        while(azulCruzando>0)
        {
            try
            {
                wait();
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(ControlTrafico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        rojoCruzando++;
        
    }

    public synchronized void entraAzul()
    {
        while(rojoCruzando>0)
        {
            try
            {
                wait();
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(ControlTrafico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        azulCruzando++;
    }

    public synchronized void saleRojo()
    {
        rojoCruzando--;
        notify();
    }

    public synchronized void saleAzul()
    {
        azulCruzando--;
        notify();
    }

}
