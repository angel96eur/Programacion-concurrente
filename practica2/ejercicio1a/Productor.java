package practica2.ejercicio1a;

public class Productor implements Runnable
{

    Cola q;

    Productor(Cola q)
    {
        this.q=q;
    }

    public void run()
    {
        while(true)     // Bucle infinito
        {
            q.put();
        }
    }
    
}
