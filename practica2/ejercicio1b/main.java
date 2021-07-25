package practica2.ejercicio1b;

public class main
{

    // 1B - Métodos de sincronización
    
    public static void main(String[] args)
    {
        
        Cola queue=new Cola();
        
        Thread Consumidor=new Thread(new Consumidor(queue));
        Thread Productor=new Thread(new Productor(queue));
        
        Consumidor.start();
        Productor.start();
        
    }

}
