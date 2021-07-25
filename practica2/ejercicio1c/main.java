package practica2.ejercicio1c;

public class main
{
    
    public static void main(String[] args) throws InterruptedException
    {

        // 1C - 1 productor y 2 consumidores
        Cola queue=new Cola();
        
        Thread Productor=new Thread(new Productor(queue));
        Thread Consumidor1=new Thread(new Consumidor(queue,1));
        Thread Consumidor2=new Thread(new Consumidor(queue,2));

        Consumidor1.setName("1");
        Consumidor2.setName("2");
        
        Productor.start();
        Consumidor1.start();
        Consumidor2.start();

    }

}
