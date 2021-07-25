package practica1.ejercicio3;
import java.util.Random;

public class Gandul extends Thread
{

    // HILO SECUNDARIO

    Gandul()
    {
        start();
    }

    @Override
    public void run()
    {

        Thread t=Thread.currentThread();
        Random r1=new Random();
        Random r2=new Random();
        int limite=((r1.nextInt(7))+2);
        boolean termina=false;
        int elegir=0;

        for(int i=0;i<limite;i++)
        {
            elegir=r2.nextInt(3);
            if(elegir==0)
            {
                System.out.println("Gandul: Me estoy vistiendo...");
            }
            if(elegir==1)
            {
                System.out.println("Gandul: Esto no me queda bien...");
            }
            if(elegir==2)
            {
                System.out.println("Gandul: Me queda un segundo...");
            }
            try
            {
                t.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                System.out.println("Gandul: ¡Que cabrón/a!");
                i=10;
                termina=true;
            }
        }
        if(termina==false)
        {
            System.out.println("Gandul: ¡Ya estoy!");
        }

    }

}
