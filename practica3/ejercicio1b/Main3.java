package practica3.ejercicio1b;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

/* sin exclusión mútua */

public class Main3
{

    // Instancia de Semáforo
    //static Semaphore s=new Semaphore(1);
    
    private static void nap(int millisecs)
    {
       
        try
        {
            Thread.sleep(millisecs);
        }
        catch(InterruptedException e)
        {
            System.err.println(e.getMessage());
        }
        
    }

    private static void operador1(PanelNivel2 d)
    {
        
        // Añade una secuencia de filas con ponLinea con pequeños naps aleatorios
        String vuelos="LLamada a Londres ";
        
        // Aleatorio para los naps
        Random r1=new Random();
        
//        try
//        {
            for(int i=0;i<20;i++)
            {
                // nap entre 2 y 4 seg.
                nap((r1.nextInt(2001)+3000));
//                s.acquire();
                d.ponLinea(vuelos + (i+1));
//                s.release();
            }
//        }
//        catch(InterruptedException ex)
//        {
//            Logger.getLogger(Main3.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    private static void operador2(PanelNivel2 d)
    {
        
        // Añade una secuencia de filas con ponLinea con pequeños naps aleatorios
        String vuelos="LLamada a París ";
        
        // Aleatorio para los naps
        Random r2=new Random();
        
//        try
//        {
            for(int i=0;i<20;i++)
            {
                // nap entre 2 y 4 seg.
                nap((r2.nextInt(2001)+3000));
//                s.acquire();
                d.ponLinea(vuelos + (i+1));
//                s.release();
            }
//        }
//        catch(InterruptedException ex)
//        {
//            Logger.getLogger(Main3.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    private static void quitaVuelos(PanelNivel2 d)
    {
        
        // Añade una secuencia de borrar la fila 0 naps aleatorios según enunciado
        for(int i=0;i<40;i++)
        {
            nap(4000);
//            try
//            {
//                s.acquire();
//            }
//            catch(InterruptedException ex)
//            {
//                Logger.getLogger(Main3.class.getName()).log(Level.SEVERE, null, ex);
//            }
            d.borraLinea(0);
//            s.release();
        }
        
    }
    
    public static void main(String [] args)
    {
        
	final PanelNivel2 p=new IPanel2();
        
        new Thread()
        {
            @Override
	    public void run()
            {
		quitaVuelos(p);
	    }
	}.start();

	new Thread()
        {
            @Override
	    public void run()
            {
		operador1(p);
	    }
	}.start();

        new Thread() {
            @Override
	    public void run()
            {
		operador2(p);
	    }
	}.start();
        
    }

}
