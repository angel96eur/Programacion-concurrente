package practica2.ejercicio1c;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Productor implements Runnable
{

    Cola q;

    Productor(Cola q)
    {
        this.q=q;
    }

    public void run()
    {
        while(true)     // Bucle infinito
        {
            try
            {
                q.put();
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
