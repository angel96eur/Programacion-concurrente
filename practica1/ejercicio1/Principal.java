package practica1.ejercicio1;

public class Principal
{

    public static void main(String[] args) throws InterruptedException
    {

        // 1 - Captura del Hilo Principal

        int n=1;
        for (int i=1;i<100;i=i+2)
        {
            Thread h=Thread.currentThread();
            for(n=1;n<50;n++)
            {
                h.setName("HiloPrincipal " + n + "\n");
                Thread.sleep(2000);
                System.out.printf(h.getName(),n);
                n++;
            }
        }

    }

}
