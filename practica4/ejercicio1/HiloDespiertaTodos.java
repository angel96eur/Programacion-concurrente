package practica4.ejercicio1;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloDespiertaTodos extends Thread
{

    Semantica s;
    
    public HiloDespiertaTodos(Semantica s)
    {
        this.s=s;
        start();
    }

    @Override
    public void run()
    {
        try
        {
            s.despiertaTodos();
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(HiloDespiertaTodos.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
