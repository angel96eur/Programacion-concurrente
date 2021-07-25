package practica3.ejercicio2b;
import java.util.concurrent.Semaphore;


public class main
{

    // Semáforos de recursos
    static Semaphore sillas=new Semaphore(5,true);
    static Semaphore boxes=new Semaphore(3,true);
    static Semaphore pantalla=new Semaphore(1,true);
    // Semáforos de citas
    static Semaphore medico=new Semaphore(0,true);
    static Semaphore enfermo=new Semaphore(0,true);
    // Semáforo para el clasificador
    static Semaphore SemaforoClasificador=new Semaphore(1,true);

    public static void main(String[] args)
    {
        // 1 hilo clasificador
        clasificador clasif=new clasificador(boxes,SemaforoClasificador);
        clasif.start();
        // 2 hilos de médicos
        for(int i=0;i<2;i++)
        {
            Thread medicos=new Thread(new medico((i+1),medico,enfermo));
            medicos.start();
        }
        // 20 hilos de enfermos
        for(int j=0;j<20;j++)
        {
            Thread enfermos=new Thread(new enfermo((j+1),medico,enfermo,pantalla,sillas,boxes,SemaforoClasificador));
            enfermos.start();
        }
    }

}
