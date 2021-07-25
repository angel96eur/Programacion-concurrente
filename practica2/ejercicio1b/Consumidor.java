package practica2.ejercicio1b;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable
{
    
    Cola q;

    Consumidor(Cola q)
    {
        this.q=q;
    }

    public void run()
    {
        while(true)     // Bucle infinito
        {
            try
            {
                q.get();
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
