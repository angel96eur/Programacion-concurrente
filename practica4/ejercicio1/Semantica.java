package practica4.ejercicio1;

public class Semantica
{

    public synchronized void espera(int id) throws InterruptedException
    {
        System.out.println("[Hilo " + id + "]: En espera");
        wait();
        System.out.println("[Hilo "+ id + "]: Salgo de la cola y termino el bloque sincronizado");
    }
    
    public synchronized void despiertaUno() throws InterruptedException
    {
        notify();
        System.out.println("[HiloDespierta1a1]: Desencolo a un hilo PERO SIGO AQUÍ");
        Thread.sleep(1000);
    }
    
    public synchronized void despiertaTodos() throws InterruptedException
    {
        notifyAll();
        System.out.println("[HiloDespiertaTodos]: Desencolo a todos los hilos PERO SIGO AQUÍ");
        Thread.sleep(1000);
    }
    
}
