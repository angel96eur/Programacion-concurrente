package practica2.ejercicio1b;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cola
{

    private int n=0;
    volatile boolean consumo=false;
    
    public synchronized int get() throws InterruptedException
    {
        // Cambiamos WHILE por IF
        if(!consumo)
        {            
            try
            {
                Thread.sleep((long)(Math.random()*500));
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Usamos la función para poner a la espera
            wait();
        }
        System.out.println("Obtengo: " + n + "\n");
        consumo=false;

        // Despertamos al hilo (solo uno)
        notify();
        return n;
    }

    public synchronized void put() throws InterruptedException
    {
        if(consumo)
        {
            wait();
        }
        System.out.println("Generando...\n");
        try
        {
            Thread.sleep((long)(Math.random()*500));
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Coloco: " + ++n);
        consumo=true;
        notify();
    }
    
}
