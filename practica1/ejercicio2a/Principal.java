package practica1.ejercicio2a;

public class Principal
{

    public static void main(String[] args)
    {

        // 2A - Trabajando con varios hilos

        HiloImprimeId h1=new HiloImprimeId(3,1);
        HiloImprimeId h2=new HiloImprimeId(4,2);
        HiloImprimeId h3=new HiloImprimeId(2,3);
        HiloImprimeId h4=new HiloImprimeId(1,4);
        HiloImprimeId h5=new HiloImprimeId(1,5);

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

    }

}
