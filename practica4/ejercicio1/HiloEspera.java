package practica4.ejercicio1;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloEspera extends Thread
{
    
    int id=0;
    Semantica s;
    
    public HiloEspera(Semantica s,int id)
    {
        this.id=id;
        this.s=s;
        start();
    }
    
    public void run()
    {
        try
        {
            s.espera(id);
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(HiloEspera.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
