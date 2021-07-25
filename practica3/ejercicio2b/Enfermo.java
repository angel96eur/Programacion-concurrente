package practica3.ejercicio2b;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class enfermo extends Thread
{

    //Atributos
    int id=0;
    Semaphore pantalla=new Semaphore(1,true);
    Semaphore medico;
    Semaphore enfermo;
    Semaphore sillas;
    Semaphore boxes;
    Semaphore clasificador;

    //Método generador del enfermo
    public enfermo(int id,Semaphore medico,Semaphore enfermo,Semaphore pantalla,Semaphore sillas,Semaphore boxes,Semaphore Clasificador)
    {
        this.id=id;
        this.pantalla=pantalla;
        this.medico=medico;
        this.enfermo=enfermo;
        this.sillas=sillas;
        this.boxes=boxes;
        this.clasificador=Clasificador;
    }

    //Método escribe
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

    //Método run
    public void run()
    {
        if (this.sillas.tryAcquire())
        {
            escribe("Enfermo: " + this.id + ". Esperare." + this.sillas.availablePermits() + " sillas libres");
            try
            {
                // Pasan al box
                // LO LLAMA AL CLASIFICADOR EN VEZ DE AL BOX
                this.clasificador.acquire();

                this.sillas.release();
                escribe("Enfermo: " + this.id + ". Me toca." + this.sillas.availablePermits() + " sillas libres");

                this.enfermo.release();
                try
                {
                    this.medico.acquire();
                }
                catch(InterruptedException ex)
                {
                    Logger.getLogger(enfermo.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Son atendidos
                escribe("Enfermo: " + this.id + ". Estoy curado");

                this.boxes.release();

                // Se van
                escribe("Enfermo: " + this.id + ". Adios");
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(enfermo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            escribe("Enfermo: " + this.id + ". No hay sillas. Adios");
        }
    }
}
