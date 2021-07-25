package practica1.ejercicio3;

public class Hilo1
{

    // 3 - Espera e interrupción de un hilo
    // MAIN

    public static void main(String[] args) throws InterruptedException
    {

        System.out.println("H1: ¿Estás ya?");
        Gandul hilo=new Gandul();
        for(int i=0;i<5;i++)
        {
            System.out.println("H1: ¿Te queda mucho?");

            // Aquí esperamos 1 segundo por 5 veces que se ejecuta el bucle FOR

            Thread.sleep(1000);
            if(hilo.isAlive()==false)
            {
                i=5;
                System.out.println("H1: ¡Por fin!");
            }
        }
        if(hilo.isAlive()==true)
        {
            hilo.interrupt();
            System.out.println("H1: ¡Yo me voy!");
        }
        System.out.println("H1: Venga, vámonos");

    }

}
