package practica2.ejercicio1a;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cola
{

    private int n=0;
    // Iniciamos con false
    volatile boolean consumo=false;
    
    public int get()
    {
        while(!consumo)
        {
            // No hace nada mientras consumo tenga el valor
        }
        try
        {
            Thread.sleep((long)(Math.random()*500));
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Obtengo: " + n + "\n");
        // Cambio el valor
        consumo=false;
        return n;
    }

    public void put()
    {
        while(consumo)
        {
            // Igual, no hace nada mientras consumo tenga el valor
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
    }
    
}
