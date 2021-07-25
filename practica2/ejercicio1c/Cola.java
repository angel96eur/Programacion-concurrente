package practica2.ejercicio1c;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cola
{

    private int n=0;
    int turno=0;
    
    public synchronized int get(int id) throws InterruptedException
    {

        while(turno!=id)
        {            
            try
            {
                Thread.sleep((long)(Math.random()*500));
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
            }
        wait();
        }
        System.out.println("[" + id + "] Obtengo: " + n + " para Consumidor " + Thread.currentThread().getName());
        turno--;

        //Aquí notificamos a todos los hilos en espera
        notifyAll();
        return n;

    }

    public synchronized void put() throws InterruptedException
    {

        while(turno!=0)
        {
            wait();
        }
        System.out.println("\nGenerando...");
        try
        {
            Thread.sleep((long)(Math.random()*500));
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Coloco: " + ++n + "\n");
        turno+=2;
        notifyAll();
    }
    
}
