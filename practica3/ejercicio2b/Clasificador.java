package practica3.ejercicio2b;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clasificador extends Thread
{
    Semaphore boxes;
    Semaphore clasificador;

    public clasificador(Semaphore boxes,Semaphore clasificador)
    {
        this.boxes=boxes;
        this.clasificador=clasificador;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                // Atiende paciente
                this.boxes.acquire();
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(clasificador.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.clasificador.release();
        }
    }

}
