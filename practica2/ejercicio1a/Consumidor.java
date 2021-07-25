package practica2.ejercicio1a;

public class Consumidor implements Runnable
{
    
    Cola q;

    Consumidor(Cola q)
    {
        this.q=q;
    }

    public void run()
    {
        while(true)     // Bucle infinito
        {
            q.get();
        }
    }

}
