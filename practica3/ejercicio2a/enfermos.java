package practica3.ejercicio2a;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class enfermos extends Thread
{

    int id=0;
    Semaphore pantalla=new Semaphore(1,true);
    Semaphore sillas;
    Semaphore boxes;
    Semaphore medico;
    Semaphore enfermo;

    public enfermos(int id,Semaphore sillas,Semaphore boxes,Semaphore medico,Semaphore enfermo,Semaphore pantalla)
    {
        this.id=id;
        this.pantalla=pantalla;
        this.sillas=sillas;
        this.boxes=boxes;
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
        if(this.sillas.tryAcquire())
        {
            escribe("Enfermo " + this.id + ": Entro, me siento   [Quedan " + this.sillas.availablePermits() + " sillas libres]");
            try
            {
                // Pasan al box
                this.boxes.acquire();
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(enfermos.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.sillas.release();
            escribe("Enfermo " + this.id + ": Me levanto, paso al box   [Quedan " + this.sillas.availablePermits() + " sillas libres]");
            this.enfermo.release();
            try
            {
                this.medico.acquire();
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(enfermos.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Son atendidos
            escribe("Enfermo " + this.id + ": Me antienden");
            this.boxes.release();
            // Se van
            escribe("Enfermo " + this.id + ": Me voy, adios");
        }
        else
        {
            escribe("Enfermo " + this.id + ": No quedan sillas, me trasladan a otro centro");
        }
    }
}
