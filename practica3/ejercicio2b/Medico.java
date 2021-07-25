package practica3.ejercicio2b;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class medico extends Thread
{

    //Atributos

    int id=0;
    Semaphore pantalla;
    Semaphore medico;
    Semaphore enfermo;

    //Método generador del médico
    public medico(int id,Semaphore medico,Semaphore enfermo)
    {
        this.id=id;
        this.medico=medico;
        this.enfermo=enfermo;
    }

    //Método de escribir
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
        while (true)
        {

        // Atiende paciente
            this.medico.release();
            try
            {
                this.enfermo.acquire();
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(medico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
