package practica2.ejercicio1c;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor implements Runnable
{
    
    Cola q;
    int id=0;

    Consumidor(Cola q,int id)
    {
        this.q=q;
        this.id=id;
    }

    public void run()
    {
        while(true)     // Bucle infinito
        {
            try
            {
                q.get(id);
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
