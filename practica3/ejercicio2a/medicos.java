package practica3.ejercicio2a;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class medicos extends Thread
{
    
    int id=0;
    Semaphore pantalla;
    Semaphore medico;
    Semaphore enfermo;

    public medicos(int id,Semaphore medico,Semaphore enfermo,Semaphore pantalla)
    {
        this.id=id;
        this.medico=medico;
        this.enfermo=enfermo;
    }
    
    protected void escribe(String mensaje)
    {
        try
        {
            this.pantalla.acquire();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(mensaje);
        this.pantalla.release();
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            this.medico.release();
            try
            {
                this.enfermo.acquire();
                //escribe("Médico " + id + ": Atiendo paciente");
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(medicos.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

}
