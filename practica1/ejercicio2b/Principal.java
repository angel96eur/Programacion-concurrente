package practica1.ejercicio2b;

public class Principal
{

    public static void main(String[] args)
    {

        // 2B - Implementando Runnable

        Thread h1,h2,h3,h4,h5;

        h1=new Thread(new HiloImprimeId(1,1));
        h2=new Thread(new HiloImprimeId(1,2));
        h3=new Thread(new HiloImprimeId(1,3));
        h4=new Thread(new HiloImprimeId(1,4));
        h5=new Thread(new HiloImprimeId(1,5));

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

    }

}
