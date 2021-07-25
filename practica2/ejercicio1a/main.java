package practica2.ejercicio1a;

public class main
{
    
    public static void main(String[] args)
    {
        
        // 1A - Productor y consumidor no sincronizados
        
        Cola queue=new Cola();
        
        Thread Consumidor=new Thread(new Consumidor(queue));
        Thread Productor=new Thread(new Productor(queue));
        
        Consumidor.start();
        Productor.start();
        
    }

}
